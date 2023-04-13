package com.spring;

import com.google.gson.Gson;
import com.spring.listener.MyListener;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {

    }/*
    public static final String GET = "GET";
    public static final String POST = "POST";
    public static final String PUT = "PUT";
    private static final String BASE_URL = "http://10.135.2.214:8090/";
    public static String sendHttpRequest(String requestUrl, String requestMethod, String requestBody) throws IOException {
        requestUrl = "http://10.135.2.214:8090/test";
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod(requestMethod);
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.setDoInput(true);
        connection.setDoOutput(true);
        //connection.setRequestProperty("Content-Type", "multipart/form-data");

        if (POST.equals(requestMethod) || PUT.equals(requestMethod)) {
            try (OutputStreamWriter writer = new OutputStreamWriter(connection.getOutputStream())) {
                writer.write(requestBody);
                writer.flush();
            }
        }

        StringBuilder response = new StringBuilder();
        InputStream inputStream = null;
        BufferedReader reader = null;

        try {
            inputStream = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            inputStream = connection.getErrorStream();
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } finally {
            if (reader != null) {
                reader.close();
            }

            if (inputStream != null) {
                inputStream.close();
            }
        }

        connection.disconnect();
        return response.toString();
    }


    public static String doGet(String requestUrl) throws IOException {
        requestUrl=BASE_URL+requestUrl;
        URL url = new URL(requestUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);

        BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        StringBuilder response = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            response.append(line);
        }

        reader.close();
        connection.disconnect();
        return  response.toString();
    }

    public static String doPost(String requestUrl, String requestBody) throws IOException {
        return sendHttpRequest(requestUrl, POST, requestBody);
    }

    public static String doPut(String requestUrl, String requestBody) throws IOException {
        return sendHttpRequest(requestUrl, PUT, requestBody);
    }

    public static void main(String[] args) throws  Exception {
        String jsonResponse = doGet("down/42");

        Gson gson = new Gson();
        Response response = gson.fromJson(jsonResponse, Response.class);

        String result = response.getResult();
        System.out.println(result);
    }


    private static class Response {
        private int code;
        private String message;
        private String result;

        public String getResult() {
            return result;
        }
    }*/
}
