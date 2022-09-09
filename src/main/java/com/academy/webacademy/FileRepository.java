package com.academy.webacademy;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class FileRepository {

    File pathToFiles = new File(Objects.requireNonNull(this.getClass().getClassLoader().getResource("db")).getPath());

    //TableServlet
    //GET
    public String listOfFiles() {
        List<String> arrayList = new ArrayList<>();
        System.out.println("I'm in listOfFiles in FileRedactor class and got here by GET /tables");
        File[] files = pathToFiles.listFiles();
        int counter = 0;
        assert files != null;
        for (File value : files) {
            if (value.isFile()) {
                arrayList.add("File number " + ++counter + " : " + value.getName());
            } else if (value.isDirectory()) {
                return "Directory: " + value.getName();
            }
        }
        return arrayList.toString();
    }

    //TablesServlet
    //GET
    public String getFileContent(String path) throws IOException {
        try (FileInputStream fileInputStream = new FileInputStream(pathToFiles + path)) {
            return new String(fileInputStream.readAllBytes());
        }
    }

    //POST
    public boolean createNewFile(String path, String body) throws IOException {
        try (FileWriter fileWriter = new FileWriter(pathToFiles + path)) {
            fileWriter.write(body);
            return true;
        }
    }

    //PUT
    public boolean updateFileContent(String path, String name) throws IOException {
        File[] files = pathToFiles.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.getName().equals(path)) {
                try (FileWriter fileWriter = new FileWriter(pathToFiles + path, true)) {
                    fileWriter.write(name);
                    return true;
                }
            } else {
                throw new IOException();
            }
        }
        return true;
    }

    //DELETE
    public boolean deleteFile(String path) throws IOException {
        File[] files = pathToFiles.listFiles();
        assert files != null;
        for (File file : files) {
            if (file.getName().equals(path)) {
                File filePath = new File(pathToFiles + path);
                return filePath.delete();
            } else {
                throw new IOException();
            }
        }
        return true;
    }
}