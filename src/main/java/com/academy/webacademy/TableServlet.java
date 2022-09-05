package com.academy.webacademy;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "table", urlPatterns = "/table/*")
public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Getting file. Going to FileRedactor/contentOfFile");
        resp.getWriter().println(new FileRepository().contentOfFile(req.getPathInfo()));
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(req.getPathInfo() + " Creating new File. Going to FileRedactor/createNewFile");
        resp.getWriter().println(new FileRepository().createNewFile(req.getPathInfo()));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(req.getPathInfo() + " Adding text to file. Going to FileRedactor/inputInFile");

        String text = GetBody.getBody(req);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        Map<String, Object> kvMap = mapper.readValue(text, typeRef);
        String name = (String) kvMap.get("content");

        resp.getWriter().println(new FileRepository().inputInFile(req.getPathInfo(), name));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(req.getPathInfo() + " Deleting file. Going to FileRedactor/deleteFile");
        resp.getWriter().println(new FileRepository().deleteFile(req.getPathInfo()));
    }

    @Override
    public void destroy() {
    }
}