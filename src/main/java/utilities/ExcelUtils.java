package utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    private static String reportFilePath;

    private static final DateTimeFormatter TS =
            DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");

    public static void initReport(String outputDir, String filePrefix) {
        try {
            Files.createDirectories(Path.of(outputDir));

            String fileName = filePrefix + "_" + LocalDateTime.now().format(TS) + ".xlsx";
            reportFilePath = Path.of(outputDir, fileName).toString();

            try (Workbook wb = new XSSFWorkbook();
                 FileOutputStream fos = new FileOutputStream(reportFilePath)) {
                wb.write(fos);
            }

            System.out.println("[Excel] Report initialized: " + reportFilePath);
        } catch (IOException e) {
            throw new RuntimeException("Failed to initialize Excel report", e);
        }
    }

    public static String getReportFilePath() {
        if (reportFilePath == null) {
            throw new IllegalStateException("Excel report not initialized. Call ExcelUtils.initReport() first.");
        }
        return reportFilePath;
    }

    public static void writeKeyValueSheet(String sheetName, Map<String, String> keyValues) {
        try (Workbook wb = loadWorkbook();
             FileOutputStream fos = new FileOutputStream(getReportFilePath())) {

            Sheet sheet = getOrCreateSheet(wb, sheetName);

            int rowIdx = nextRowIndex(sheet);

            Row header = sheet.createRow(rowIdx++);
            header.createCell(0).setCellValue("Field");
            header.createCell(1).setCellValue("Value");
            applyHeaderStyle(wb, header);

            // Data rows
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
        return last + 2;
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
}