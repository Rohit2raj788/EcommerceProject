package utilities;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtils {
    private Workbook workbook;
    private Sheet sheet;
    private String filePath;  // Dynamic file path

    // Constructor to load a specific Excel file and sheet
    public ExcelUtils(String filePath, String sheetName) throws IOException {
        this.filePath = filePath;
        FileInputStream file = new FileInputStream(filePath);
        workbook = new XSSFWorkbook(file);
        sheet = workbook.getSheet(sheetName);
        file.close();
    }

    // Get Single Data (Row, Column)
    public String getCellData(int row, int column) {
        return sheet.getRow(row).getCell(column).getStringCellValue();
    }

    // Get Multiple Data (Full Row)
    public String[] getRowData(int row) {
        Row currentRow = sheet.getRow(row);
        int totalCells = currentRow.getLastCellNum();
        String[] rowData = new String[totalCells];

        for (int i = 0; i < totalCells; i++) {
            rowData[i] = currentRow.getCell(i).getStringCellValue();
        }
        return rowData;
    }

    // Get Multiple Data (Full Column)
    public String[] getColumnData(int column) {
        int totalRows = sheet.getLastRowNum() + 1;
        String[] columnData = new String[totalRows];

        for (int i = 0; i < totalRows; i++) {
            columnData[i] = sheet.getRow(i).getCell(column).getStringCellValue();
        }
        return columnData;
    }

    // Write Data to Excel
    public void setCellData(String data, int row, int column) throws IOException {
        Row currentRow = sheet.getRow(row);
        if (currentRow == null) {
            currentRow = sheet.createRow(row);
        }
        Cell cell = currentRow.getCell(column);
        if (cell == null) {
            cell = currentRow.createCell(column);
        }
        cell.setCellValue(data);

        // Save changes
        FileOutputStream fileOut = new FileOutputStream(filePath);
        workbook.write(fileOut);
        fileOut.close();
    }

    // Close Workbook
    public void closeExcel() throws IOException {
        workbook.close();
    }
}
