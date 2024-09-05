package org.example;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.example.models.Person;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class ExcelFileReadWrite {

    public  void writingToExcelFileFromList(String fileName, List<Person> persons) throws IOException {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet personSheet = workbook.createSheet("personList");
        AtomicInteger rowNo = new AtomicInteger();
        persons.forEach(person -> {
            XSSFRow row = personSheet.createRow(rowNo.getAndIncrement());
            int cellNo = 0;
            for (Field field : person.getClass().getDeclaredFields()) {
                field.setAccessible(true);
                XSSFCell cell = row.createCell(cellNo);
                cellNo = cellNo + 1;
                try {
                    Object value = field.get(person);

                    if (value instanceof String) {
                        cell.setCellValue((String) value);

                    } else if (value instanceof Integer) {
                        cell.setCellValue((Integer) value);

                    }
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        FileOutputStream fouts = new FileOutputStream(fileName);
        workbook.write(fouts);
        System.out.println("completed writing the file");


    }

    public  void readingFromExelFile(String fileName) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(fileName);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet personList = workbook.getSheet("personList");
        for (Row row : personList) {
            Iterator<Cell> cellIterator = row.cellIterator();
            while (cellIterator.hasNext()) {
                Cell currCell = cellIterator.next();

                switch (currCell.getCellType()) {
                    case CellType.NUMERIC:
                        System.out.print(currCell.getNumericCellValue() + "\t");
                        break;

                    case CellType.STRING:
                        System.out.print(currCell.getStringCellValue() + "\t");
                        break;
                }

            }
            System.out.println("");

        }
        fileInputStream.close();


//        workbook.getSheet()
    }
}
