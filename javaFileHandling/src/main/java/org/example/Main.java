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
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
//        Map<String,Object> person = new HashMap<>();

        List<Person> persons = new ArrayList<>();
        persons.add(new Person(1, "Sahil_1", 21));
        persons.add(new Person(2, "Sahil_2", 22));
        persons.add(new Person(3, "Sahil_3", 23));
        persons.add(new Person(4, "Sahil_4", 24));
        persons.add(new Person(5, "Sahil_5", 25));
        persons.add(new Person(6, "Sahil_6", 26));

        FileReadWrite fileReadWrite = new FileReadWrite();
        CsvReadWrite csvReadWrite = new CsvReadWrite();
        ExcelFileReadWrite excelFileReadWrite = new ExcelFileReadWrite();

        try {
            System.out.println("Writing to excel file from list");
            excelFileReadWrite.writingToExcelFileFromList("otherFile.xlsx", persons);
            System.out.println("\n\nReading from excel file");
            excelFileReadWrite.readingFromExelFile("otherFile.xlsx");


            System.out.println("\n\nAdd anything to a file from command Line \n ( PRESS ENTER TWICE TO STOP WRITING )");
            fileReadWrite.readFromCmdAndWriteToFile("demo.txt");
            System.out.println("\n\nRead From the written file");
            fileReadWrite.readFile("demo.txt");


            System.out.println("\n\n writing to csv file using list");
            csvReadWrite.writeToCsvFromList("newFile.csv",persons);
            System.out.println("\n\nRwading Entries from csv file");
            csvReadWrite.readCSVFile("newFile.csv").forEach(System.out::println);

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


}