package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.checkerframework.checker.units.qual.K;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ExcelUtils {
    private static String reportFilePath;
    private static String outputDir;
    private static String filePrefix;
    private static String filePageName;

    public static void initReport(String outputDirectory, String prefix, String pageName) {
        outputDir = outputDirectory;
        filePrefix = prefix;
        filePageName = sanitizeFileName(pageName);
        reportFilePath = null;
        System.out.println("[Excel] Report initialized (lazy mode) for page: " + filePageName);
    }
    private static String sanitizeFileName(String name) {
        if (name == null) return "";
        return name.replaceAll("[\\\\/:*?\"<>|\\s]+", "_");
    }
    private static void ensureReportExists() {
        if (reportFilePath == null) {
            try {
                Files.createDirectories(Path.of(outputDir));
                String pagePart = (filePageName == null || filePageName.isEmpty()) ? "" : ("_" + filePageName);
                String fileName = filePrefix + pagePart + "_"  + ".xlsx";
                reportFilePath = Path.of(outputDir, fileName).toString();
                try (Workbook wb = new XSSFWorkbook();
                     FileOutputStream fos = new FileOutputStream(reportFilePath)) {
                    wb.write(fos);
                }
                Log.info("[Excel] Report created on first write: " + reportFilePath);
            } catch (IOException e) {
                throw new RuntimeException("Failed to initialize Excel report", e);
            }
        }
    }
    public static String getReportFilePath() {
        if (reportFilePath == null) {
            throw new IllegalStateException("Excel report not initialized. Call ExcelUtils.initReport() first.");
        }
        ensureReportExists();
        return reportFilePath;
    }
    public static void writeKeyValueSheet(String sheetName, Map<String, String> keyValues) {
        ensureReportExists();
        try (Workbook wb = loadWorkbook();
             FileOutputStream fos = new FileOutputStream(getReportFilePath())) {
            Sheet sheet = getOrCreateSheet(wb, sheetName);
            int rowIdx = nextRowIndex(sheet);
            Row header = sheet.createRow(rowIdx++);
            header.createCell(0).setCellValue("Field");
            header.createCell(1).setCellValue("Value");
            applyHeaderStyle(wb, header);
            for (Map.Entry<String, String> e : keyValues.entrySet()) {
                Row r = sheet.createRow(rowIdx++);
                r.createCell(0).setCellValue(e.getKey());
                r.createCell(1).setCellValue(e.getValue() == null ? "" : e.getValue());
            }
            autosize(sheet, 2);
            wb.write(fos);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write key-value sheet: " + sheetName, e);
        }
    }
    public static void writeTable(String sheetName, List<String> headers, List<List<String>> rows) {
        ensureReportExists();
        try (Workbook wb = loadWorkbook();
             FileOutputStream fos = new FileOutputStream(getReportFilePath())) {
            Sheet sheet = getOrCreateSheet(wb, sheetName);
            int rowIdx = nextRowIndex(sheet);
            Row headerRow = sheet.createRow(rowIdx++);
            for (int i = 0; i < headers.size(); i++) {
                headerRow.createCell(i).setCellValue(headers.get(i));
            }
            applyHeaderStyle(wb, headerRow);
            for (List<String> row : rows) {
                Row r = sheet.createRow(rowIdx++);
                for (int c = 0; c < row.size(); c++) {
                    r.createCell(c).setCellValue(row.get(c) == null ? "" : row.get(c));
                }
            }
            autosize(sheet, headers.size());
            wb.write(fos);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write table sheet: " + sheetName, e);
        }
    }
    public static void writeList(String sheetName, String columnHeader, List<String> values) {
        ensureReportExists();
        try (Workbook wb = loadWorkbook();
            FileOutputStream fos = new FileOutputStream(getReportFilePath())) {
            Sheet sheet = getOrCreateSheet(wb, sheetName);
            int rowIdx = nextRowIndex(sheet);
            Row headerRow = sheet.createRow(rowIdx++);
            headerRow.createCell(0).setCellValue(columnHeader);
            applyHeaderStyle(wb, headerRow);
            for (String v : values) {
                Row r = sheet.createRow(rowIdx++);
                r.createCell(0).setCellValue(v == null ? "" : v);
            }
            autosize(sheet, 1);
            wb.write(fos);
        } catch (IOException e) {
            throw new RuntimeException("Failed to write list sheet: " + sheetName, e);
        }
    }
    private static Workbook loadWorkbook() throws IOException {
        File f = new File(getReportFilePath());
        if (!f.exists()) {
            return new XSSFWorkbook();
        }
        try (FileInputStream fis = new FileInputStream(f)) {
            return WorkbookFactory.create(fis);
        }
    }
    private static Sheet getOrCreateSheet(Workbook wb, String name) {
        Sheet sheet = wb.getSheet(name);
        return (sheet != null) ? sheet : wb.createSheet(name);
    }
    private static int nextRowIndex(Sheet sheet) {
        int last = sheet.getLastRowNum();
        if (last == 0 && sheet.getRow(0) == null) return 0;
        return last + 2; // keep a blank row gap between blocks
    }
    private static void applyHeaderStyle(Workbook wb, Row header) {
        CellStyle style = wb.createCellStyle();
        Font font = wb.createFont();
        font.setBold(true);
        style.setFont(font);
        for (Cell cell : header) {
            cell.setCellStyle(style);
        }
    }
    private static void autosize(Sheet sheet, int columns) {
        for (int i = 0; i < columns; i++) {
            sheet.autoSizeColumn(i);
            sheet.setColumnWidth(i, sheet.getColumnWidth(i) + 500);
        }
    }
    private static Workbook openWorkbook(String filePath) throws IOException {
        File f = new File(filePath);
        if (!f.exists()) throw new FileNotFoundException("File not found: " + filePath);
        try (FileInputStream fis = new FileInputStream(f)) {
            return WorkbookFactory.create(fis);
        }
    }
    public static List<String> getSheetNames(String filePath) {
        try (Workbook wb = openWorkbook(filePath)) {
            List<String> names = new ArrayList<>();
            for (int i = 0; i < wb.getNumberOfSheets(); i++) {
                names.add(wb.getSheetName(i));
            }
            return names;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read sheet names from: " + filePath, e);
        }
    }
    public static List<List<String>> readTable(String filePath, String sheetName) {
        try (Workbook wb = openWorkbook(filePath)) {
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }
            int maxCols = findMaxColumns(sheet);
            List<List<String>> table = new ArrayList<>();
            for (Row row : sheet) {
                if (row == null) continue;
                if (isRowCompletelyBlank(row, maxCols)) continue;
                List<String> line = new ArrayList<>(maxCols);
                for (int c = 0; c < maxCols; c++) {
                    Cell cell = row.getCell(c, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                    line.add(cellToString(cell).trim());
                }
                table.add(line);
            }
            return table;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read table from: " + filePath + " / " + sheetName, e);
        }
    }
    public static List<String> readList(String filePath, String sheetName, int columnIndex, boolean skipHeader) {
        try (Workbook wb = openWorkbook(filePath)) {
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }
            List<String> values = new ArrayList<>();
            int startRow = skipHeader ? 1 : 0;
            int lastRow = sheet.getLastRowNum();
            for (int r = startRow; r <= lastRow; r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                Cell cell = row.getCell(columnIndex, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
                String v = cellToString(cell).trim();
                if (v.isEmpty()) continue; // ignore blanks (optional; remove if you want blanks)
                values.add(v);
            }
            return values;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read list from: " + filePath + " / " + sheetName, e);
        }
    }
    public static Map<String, String> readKeyValueSheet(String filePath, String sheetName, boolean skipHeader) {
        try (Workbook wb = openWorkbook(filePath)) {
            Sheet sheet = wb.getSheet(sheetName);
            if (sheet == null) {
                throw new IllegalArgumentException("Sheet not found: " + sheetName);
            }
            Map<String, String> map = new LinkedHashMap<>();
            int startRow = skipHeader ? 1 : 0;
            int lastRow = sheet.getLastRowNum();
            for (int r = startRow; r <= lastRow; r++) {
                Row row = sheet.getRow(r);
                if (row == null) continue;
                String key = cellToString(row.getCell(0, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).trim();
                String val = cellToString(row.getCell(1, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL)).trim();
                if (key.isEmpty()) continue; // skip rows without a key
                map.put(key, val);
            }
            return map;
        } catch (IOException e) {
            throw new RuntimeException("Failed to read key-value sheet from: " + filePath + " / " + sheetName, e);
        }
    }
    private static boolean isRowCompletelyBlank(Row row, int maxCols) {
        if (row == null) return true;
        for (int i = 0; i < maxCols; i++) {
            Cell cell = row.getCell(i, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
            if (cell != null && !cellToString(cell).trim().isEmpty()) {
                return false;
            }
        }
        return true;
    }
    private static int findMaxColumns(Sheet sheet) {
        int max = 0;
        for (Row row : sheet) {
            if (row != null && row.getLastCellNum() > max) {
                max = row.getLastCellNum();
            }
        }
        return Math.max(max, 1);
    }
    private static String cellToString(Cell cell) {
        if (cell == null) return "";
        switch (cell.getCellType()) {
            case STRING:
                return cell.getStringCellValue();
            case NUMERIC:
                if (DateUtil.isCellDateFormatted(cell)) {
                    return cell.getLocalDateTimeCellValue().toString();
                } else {
                    double d = cell.getNumericCellValue();
                    if (Math.floor(d) == d) return String.valueOf((long) d);
                    return String.valueOf(d);
                }
            case BOOLEAN:
                return String.valueOf(cell.getBooleanCellValue());
            case FORMULA:
                try {
                    FormulaEvaluator evaluator = cell.getSheet().getWorkbook().getCreationHelper().createFormulaEvaluator();
                    CellValue cv = evaluator.evaluate(cell);
                    if (cv == null) return "";
                    switch (cv.getCellType()) {
                        case STRING:  return cv.getStringValue();
                        case NUMERIC:
                            double d = cv.getNumberValue();
                            if (Math.floor(d) == d) return String.valueOf((long) d);
                            return String.valueOf(d);
                        case BOOLEAN: return String.valueOf(cv.getBooleanValue());
                        default:      return "";
                    }
                } catch (Exception e) {
                    return cell.getCellFormula();
                }
            case BLANK:
            case _NONE:
            case ERROR:
            default:
                return "";
        }
    }
}
