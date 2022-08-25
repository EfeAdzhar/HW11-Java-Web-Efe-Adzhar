package com.academy.webacademy;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet(name = "table", urlPatterns = "/table/*")
public class TableServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println("Getting file. Going to FileRedactor/contentOfFile");
        new FileRedactor().contentOfFile(req.getPathInfo(), resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(req.getPathInfo() + " Creating new File. Going to FileRedactor/createNewFile");
        new FileRedactor().createNewFile(req.getPathInfo(), resp);
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(req.getPathInfo() + " Adding text to file. Going to FileRedactor/inputInFile");
        new FileRedactor().inputInFile(req.getPathInfo(), req, resp);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        resp.getWriter().println(req.getPathInfo() + " Deleting file. Going to FileRedactor/deleteFile");
        new FileRedactor().deleteFile(req.getPathInfo(), req, resp);
    }

    @Override
    public void destroy() {
    }
}