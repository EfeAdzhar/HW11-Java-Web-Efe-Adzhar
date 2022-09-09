package com.academy.webacademy;

import jakarta.servlet.http.HttpServletRequest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class RequestUtil {
    static String parseBody(HttpServletRequest request) {
        StringBuilder bodyBuilder = new StringBuilder();
        try (InputStream inputStream = request.getInputStream();
             BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))) {
            char[] charBuffer = new char[128];
            int bytesRead;
            while ((bytesRead = bufferedReader.read(charBuffer)) > 0) {
                bodyBuilder.append(charBuffer, 0, bytesRead);
            }
        } catch (IOException ex) {
            System.err.println("Cannot read body");
        }
        return bodyBuilder.toString();
    }
}