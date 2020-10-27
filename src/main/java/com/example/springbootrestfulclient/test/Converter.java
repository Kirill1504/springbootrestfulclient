package com.example.springbootrestfulclient.test;

import com.example.springbootrestfulclient.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class Converter {
    public static void main(String[] args) throws IOException {
        toJSON(new User(3L, "James", "Brown", (byte) 21));
        toJavaObject();
    }

    private final static String baseFile = "user.json";

    public static void toJSON(User user) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File(baseFile), user);
        System.out.println("json created!");
    }

    public static User toJavaObject() throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.readValue(new File(baseFile), User.class);
    }

}
