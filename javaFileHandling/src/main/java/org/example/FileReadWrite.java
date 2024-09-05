package org.example;

import java.io.*;

public class FileReadWrite {
    public void readFile(String fileName) throws IOException {
        FileReader fileReader = new FileReader(fileName);
        BufferedReader bf = new BufferedReader(fileReader);

        while(bf.ready()){
            System.out.println(bf.readLine());

        }

        bf.close();


    }


    public void readFromCmdAndWriteToFile(String fileName) throws IOException {
        FileWriter fileWriter = new FileWriter(fileName,true);
        BufferedReader bufferedInputReader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Write what you want to add inside the file");

        while (true){
            String s = bufferedInputReader.readLine() + "\n";
            if(s.trim().isEmpty()){
                break;
            }

            fileWriter.write(s);
        }
        bufferedInputReader.close();
        fileWriter.close();
    }
}
