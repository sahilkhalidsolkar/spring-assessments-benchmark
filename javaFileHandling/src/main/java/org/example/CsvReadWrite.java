package org.example;

import org.example.models.Person;

import java.io.*;
import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class CsvReadWrite {

    public List<Person> readCSVFile(String filename) throws IOException {
        List<Person> persons = new ArrayList<>();
        FileReader fileReader = new FileReader(filename);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        bufferedReader.readLine(); // to skip the heading line

        while (bufferedReader.ready()) {
            String line = bufferedReader.readLine();
            String[] split = line.split(",");
            int id = Integer.parseInt(split[0]);
            String name = split[1];
            int age = Integer.parseInt(split[2]);
            persons.add(new Person(id, name, age));

        }

        bufferedReader.close();
        fileReader.close();
        return persons;
    }

    public void writeToCsvFromList(String fileName, List<Person> persons) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName, true);
        persons.forEach((person)->{
            String line =  person.getId() + "," + person.getName() +","+person.getAge()+"\n";
            try {
                fileWriter.write(line);
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        });
        fileWriter.close();
    }


}
