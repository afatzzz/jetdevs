package com.jet.devs.helper;

import com.jet.devs.model.DataExcel;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Author: ab
 * Date Time: 07/11/23
 */
public class ExcelHelper {
    public static String TYPE = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    static String[] HEADERs = { "Id", "Title", "Description", "Published" };
    static String SHEET = "Sheet1";

    public static boolean hasExcelFormat(MultipartFile file) {

        if (!TYPE.equals(file.getContentType())) {
            return false;
        }

        return true;
    }

    public static List<DataExcel> excelToTutorials(InputStream is, String fileName) {
        try {
            Workbook workbook = new XSSFWorkbook(is);

            Sheet sheet = workbook.getSheet(SHEET);
            Iterator<Row> rows = sheet.iterator();

            List<DataExcel> dataExcels = new ArrayList<>();

            int rowNumber = 0;
            while (rows.hasNext()) {
                Row currentRow = rows.next();

                // skip header
                if (rowNumber == 0) {
                    rowNumber++;
                    continue;
                }

                Iterator<Cell> cellsInRow = currentRow.iterator();

                DataExcel dataExcel = new DataExcel();

                int cellIdx = 0;
                while (cellsInRow.hasNext()) {
                    Cell currentCell = cellsInRow.next();

                    switch (cellIdx) {
                        case 0:
                            dataExcel.setId((long) currentCell.getNumericCellValue());
                            break;

                        case 1:
                            dataExcel.setTitle(currentCell.getStringCellValue());
                            break;

                        case 2:
                            dataExcel.setDescription(currentCell.getStringCellValue());
                            break;

                        case 3:
                            dataExcel.setPublished(currentCell.getBooleanCellValue());
                            break;

                        default:
                            break;
                    }
                    dataExcel.setFileName(fileName);
                    cellIdx++;
                }

                dataExcels.add(dataExcel);
            }

            workbook.close();

            return dataExcels;
        } catch (IOException e) {
            throw new RuntimeException("fail to parse Excel file: " + e.getMessage());
        }
    }
}
