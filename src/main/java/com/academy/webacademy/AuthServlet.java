package com.academy.webacademy;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "login", value = "/login")

public class AuthServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = RequestUtil.parseBody(req);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        Map<String, Object> kvMap = mapper.readValue(json, typeRef);
        String name = (String) kvMap.get("name");
        String password = (String) kvMap.get("password");

        HttpSession session = req.getSession();
        session.setAttribute("name", name);
        session.setAttribute("password", password);
        resp.getWriter().println("login: " + session.getAttribute("name") +"\npassword: "+ session.getAttribute("password"));
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}