package com.example.springbootrestfulclient.get;

import com.example.springbootrestfulclient.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class GetPOJOWithHeaderExample {

    static final String URL_EMPLOYEES = "http://91.241.64.178:7081/api/users";

    public static void main(String[] args) throws IOException {

        /*HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        RestTemplate restTemplate = new RestTemplate();

        ResponseEntity<String> response = restTemplate.exchange(URL_EMPLOYEES, HttpMethod.GET, entity, String.class);
        String str = response.getHeaders().toString().split(",")[0];
        String sessionId = str.substring(13, str.lastIndexOf('"'));
        System.out.println(sessionId);


        HttpHeaders headers1 = new HttpHeaders();
        //headers.set("Cookie", sessionId);
        headers1.setContentType(MediaType.APPLICATION_JSON);
        headers1.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers1.add("Cookie", sessionId);
        User user = new User(3L, "James", "Brown", (byte) 21);
        RestTemplate restTemplate1 = new RestTemplate();

        HttpEntity<User> requestBody = new HttpEntity<>(user, headers1);
        ResponseEntity<User> result = restTemplate1.exchange(URL_EMPLOYEES, HttpMethod.POST, requestBody, User.class);*/

        //CookieHandler.setDefault(new CookieManager(null, CookiePolicy.ACCEPT_ALL));
        String code = "";
        URL url = new URL("http://91.241.64.178:7081/api/users");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");

        String cookie = con.getHeaderField("Set-Cookie");
        System.out.println(cookie);

        url = new URL("http://91.241.64.178:7081/api/users");
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Cookie", cookie);
        con.setInstanceFollowRedirects(false);
        con.setDoOutput(true);
        User user = new User(3L, "James", "Brown", (byte) 21);
        String jsonInputString = "{\"id\":3,\"name\":\"James\",\"lastName\":\"Brown\",\"age\":21}";
        /*//ObjectOutputStream objectOutputStream = new ObjectOutputStream(con.getOutputStream());
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(con.getOutputStream(), user);
        objectOutputStream.writeObject(user);
        objectOutputStream.flush();
        objectOutputStream.close();*/
        OutputStream os = con.getOutputStream();
        byte[] input = jsonInputString.getBytes(StandardCharsets.UTF_8);
        os.write(input, 0, input.length);

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            code += response.toString();
        }

        url = new URL("http://91.241.64.178:7081/api/users");
        con = (HttpURLConnection) url.openConnection();
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("PUT");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Cookie", cookie);
        con.setInstanceFollowRedirects(false);
        con.setDoOutput(true);
        String jsonInputString2 = "{\"id\":3,\"name\":\"Thomas\",\"lastName\":\"Shelby\",\"age\":21}";
        OutputStream os1 = con.getOutputStream();
        byte[] input1 = jsonInputString2.getBytes(StandardCharsets.UTF_8);
        os1.write(input1, 0, input1.length);
        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            code += response.toString();
        }

        url = new URL("http://91.241.64.178:7081/api/users/3");
        con = (HttpURLConnection) url.openConnection();
        con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("DELETE");
        con.setRequestProperty("Content-Type", "application/json; utf-8");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Cookie", cookie);
        con.setInstanceFollowRedirects(false);
        con.setDoOutput(true);

        try(BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder response = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                response.append(responseLine.trim());
            }
            System.out.println(response.toString());
            code += response.toString();
        }

        System.out.println(code);
    }
}
