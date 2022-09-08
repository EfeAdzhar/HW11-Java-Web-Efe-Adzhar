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

    String name;
    String password;

    //FIXED:
    // to remove init/destroy methods

    //FIXED:
    // Remove new LoginFilter().session(session, resp); I don't know why you need it

    //MAYBE FIXED:
    // let's use constants for name/password attribute fields

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = GetBody.getBody(req);
        ObjectMapper mapper = new ObjectMapper();
        TypeReference<HashMap<String, Object>> typeRef = new TypeReference<>() {
        };
        Map<String, Object> kvMap = mapper.readValue(json, typeRef);
        name = (String) kvMap.get("name");
        password = (String) kvMap.get("password");

        HttpSession session = req.getSession();
        session.setAttribute("name", name);
        session.setAttribute("password", password);
        resp.getWriter().println("login: " + session.getAttribute("name") +"\npassword: "+ session.getAttribute("password"));
        resp.setStatus(200);
    }
}