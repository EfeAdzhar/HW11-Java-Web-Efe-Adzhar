package com.academy.webacademy;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


class FileRepository {
    // FIXED:
    // Please return here and other methods content and take arguments that you need. (decouple web from java business logic)
    public String listOfFiles() {

        //FIXED:
        // Should be files in resources directory with relative path. Please check Gradle structure

        File file = new File("/Users/efe/Desktop/web-academy/src/main/resources/db");
        System.out.println("I'm in listOfFiles in FileRedactor class and got here by GET /tables");
        File[] files = file.listFiles();
        int counter = 0;
        assert files != null;
        for (File value : files) {
            if (value.isFile()) {
                return "File number " + ++counter + " : " + value.getName();
            } else if (value.isDirectory()) {
                return "Directory: " + value.getName();
            }
        }
        return "Was in readFile in FileRedactor Class and got Get from GetName file";
    }

    //MARK: GET
    public String contentOfFile(String path) throws IOException {
        File file = new File("/Users/efe/Desktop/web-academy/src/main/resources/db");
        try (FileInputStream fileInputStream = new FileInputStream(file + path)) {
            return new String(fileInputStream.readAllBytes());
        }
    }

    //MAYBE FIXED:
    // do not forget to move file and remove dependency on web classes

    //MARK : POST
    public String createNewFile(String path) throws IOException {
        File file = new File("/Users/efe/Desktop/web-academy/src/main/resources/db");
        Path post = Path.of(file + path);
        Files.createFile(post);
       return "Created new file" + path;
    }

    //MARK: PUT
    public String inputInFile(String path, String name) throws IOException {
        File file = new File("/Users/efe/Desktop/web-academy/src/main/resources/db");
        try (FileWriter fileWriter = new FileWriter(file + path, true)) {
            fileWriter.write(name + " ");
            return "PUT successful";
        }
    }

    //MARK: DELETE
    public String deleteFile(String path) {
        File file = new File("/Users/efe/Desktop/web-academy/src/main/resources/db" + path);
        if (file.delete()) {
            return "File " + file.getName() + " deleted successfully";
        } else {
            return "Error in deleting file";
        }
    }
}