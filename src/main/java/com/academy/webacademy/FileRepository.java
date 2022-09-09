package com.academy.webacademy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

class FileRepository {

    File file = new File("/Users/efe/Desktop/web-academy/src/main/resources/db");


    //MARK: TableServlet
    //MARK: GET
    public String listOfFiles() {
        List<String> arrayList = new ArrayList<>();
        System.out.println("I'm in listOfFiles in FileRedactor class and got here by GET /tables");
        File[] files = file.listFiles();
        int counter = 0;
        assert files != null;
        for (File value : files) {
            if (value.isFile()) {
                arrayList.add("File number " + ++counter + " : " + value.getName());
            }
            else if (value.isDirectory()) {
                return "Directory: " + value.getName();
            }
        }
        return arrayList.toString();
    }

    public boolean postInFile(String path, String body) throws IOException {
       try(FileWriter fileWriter = new FileWriter(file + path)) {
           fileWriter.write(body);
           return true;
       }
    }


    //MARK: TablesServlet
    //MARK: GET
    public String getFileContent(String path) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file + path)) {
            return new String(fileInputStream.readAllBytes());
        }
    }

    //MARK : POST
    public String createNewFile(String path) throws IOException {
        Path post = Path.of(file + path);
        Files.createFile(post);
        return "Created new file" + path;
    }

    //MARK: PUT
    public boolean updateFileContent(String path, String name) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file + path, true)) {
            fileWriter.write(name + " ");
            return true;
        }
    }

    //MARK: DELETE
    public String deleteFile(String path) {
        File filePath = new File(file + path);
        if (filePath.delete()) {
            return "File " + file.getName() + " deleted successfully";
        } else {
            return "Error in deleting file";
        }
    }
}