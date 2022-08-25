package com.academy.webacademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

class FileRedactor {


    public void listOfFiles(HttpServletResponse response) throws IOException {
        File file = new File("/Users/efe/Desktop/web-academy/src/main/java/com/academy/webacademy/db");
        response.getWriter().println("I'm in listOfFiles in FileRedactor class and got here by GET /tables");
        File[] files = file.listFiles();
        int counter = 0;
        assert files != null;
        for (File value : files) {
            if (value.isFile()) {
                response.getWriter().println("File number " + ++counter + " : " + value.getName());
            } else if (value.isDirectory()) {
                response.getWriter().println("Directory: " + value.getName());
            }
        }
        response.getWriter().println("Was in readFile in FileRedactor Class and got Get from GetName file");
    }

    //MARK: GET
    public void contentOfFile(String path, HttpServletResponse response) throws IOException {
        File file = new File("/Users/efe/Desktop/web-academy/src/main/java/com/academy/webacademy/db");
        response.getWriter().println("am i here?");
        try (FileInputStream fileInputStream = new FileInputStream(file + path)) {
            response.getWriter().println(new String(fileInputStream.readAllBytes()));
            response.setStatus(200);
        }
    }

    //MARK : POST
    public void createNewFile(String path, HttpServletResponse response) throws IOException {
        File file = new File("/Users/efe/Desktop/web-academy/src/main/java/com/academy/webacademy/db");
        Path post = Path.of(file + path);
        Files.createFile(post);
        response.getWriter().println("Created new file" + path);
    }

    //MARK: PUT
    public void inputInFile(String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("/Users/efe/Desktop/web-academy/src/main/java/com/academy/webacademy/db");
        String text = GetBody.getBody(request);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        Map<String, Object> kvMap = mapper.readValue(text, typeRef);
        String name = (String) kvMap.get("content");

        try (FileWriter fileWriter = new FileWriter(file + path, true)) {
            fileWriter.write(name + " ");
            response.getWriter().println("PUT successful");
        }
    }

    //MARK: DELETE
    public void deleteFile(String path, HttpServletRequest request, HttpServletResponse response) throws IOException {
        File file = new File("/Users/efe/Desktop/web-academy/src/main/java/com/academy/webacademy/db" + path);
        if (file.delete()) {
            response.getWriter().println("File " + request.getPathInfo() + " deleted successfully");
        } else {
            response.getWriter().println("Error in deleting file");
        }
    }
}