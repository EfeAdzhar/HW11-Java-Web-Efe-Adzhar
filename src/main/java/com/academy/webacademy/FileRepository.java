package com.academy.webacademy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

class FileRepository {

    private final File file = new File("/Users/efe/Desktop/web-academy/src/main/resources/db");

    //TableServlet
    //GET
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

    //TablesServlet
    //GET
    public String getFileContent(String path) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(file + path)) {
            return new String(fileInputStream.readAllBytes());
        }
    }

    //POST
    public boolean createNewFile(String path, String body) throws IOException {
        try(FileWriter fileWriter = new FileWriter(file + path)) {
            fileWriter.write(body);
            return true;
        }
    }

    //PUT
    public boolean updateFileContent(String path, String name) throws IOException {
        try (FileWriter fileWriter = new FileWriter(file + path, true)) {
            fileWriter.write(name + " ");
            return true;
        }
    }

    //DELETE
    public boolean deleteFile(String path) {
        File filePath = new File(file + path);
        return filePath.delete();
    }
}