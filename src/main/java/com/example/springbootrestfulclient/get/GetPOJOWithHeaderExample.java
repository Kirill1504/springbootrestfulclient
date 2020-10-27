package com.example.springbootrestfulclient.get;

import com.example.springbootrestfulclient.model.User;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.util.*;

public class GetPOJOWithHeaderExample {

    static final String URL_EMPLOYEES = "http://91.241.64.178:7081/api/users";

    public static void main(String[] args) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES, HttpMethod.GET, entity, String.class);
        String str = response.getHeaders().toString().split(",")[0];
        String sessionId = str.substring(13, str.lastIndexOf('"'));
        System.out.println(sessionId);
        System.out.println(response);


        HttpHeaders headers1 = new HttpHeaders();
        //headers.set("Cookie", sessionId);
        headers1.setContentType(MediaType.APPLICATION_JSON);
        headers1.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        headers1.add("Cookie", sessionId);
        User user = new User(3L, "James", "Brown", (byte) 21);
        RestTemplate restTemplate1 = new RestTemplate();

        HttpEntity<User> requestBody = new HttpEntity<>(user, headers1);
        ResponseEntity<User> result = restTemplate1.exchange(URL_EMPLOYEES, HttpMethod.POST, requestBody, User.class);

        /*CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));*/

        /*URL url = new URL("http://91.241.64.178:7081/api/users");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        String cookie = con.getHeaderField("Set-Cookie");
        System.out.println(cookie);

        url = new URL("http://91.241.64.178:7081/api/users");
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        con.setRequestProperty("Cookie", cookie);
        con.setInstanceFollowRedirects(false);
        con.setDoOutput(true);
        User user = new User(3L, "James", "Brown", (byte) 21);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(con.getOutputStream());
        objectOutputStream.writeObject(user);
        objectOutputStream.flush();
        objectOutputStream.close();

        System.out.println(con.getResponseCode());*/
    }
}
