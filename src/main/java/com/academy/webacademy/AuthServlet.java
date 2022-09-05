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

    // to remove init/destroy methods
    // Remove new LoginFilter().session(session, resp); I don't know why you need it
    // let's use constants for name/password attribute fields

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = GetBody.getBody(req);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        Map<String, Object> kvMap = mapper.readValue(json, typeRef);
        final String name = (String) kvMap.get("name");

        HttpSession session = req.getSession();
        session.setAttribute("name", name);
        resp.getWriter().println("login:" + session.getAttribute("name"));
        resp.setStatus(200);
    }
}