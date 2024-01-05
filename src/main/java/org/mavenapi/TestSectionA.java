package org.mavenapi;


import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.databind.ObjectMapper;

import javafx.util.Callback;
import javafx.util.StringConverter;

public class TestSectionA {

    private static final String api = "https://pastebin.com/raw/TytE0smz?fbclid=IwAR25BvK84-yTiBeIpdgXX3KcfbOuWB9pmE0NRqyEzkaypU9ycVGbJYFYkes";

    //Handles the APIRequest and returns a class CSC200
    public static CSC200 APIRequest()
    {
        try
        {
            // Step 1: Make HTTP request
            URL url = new URL(api);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            // Read the response
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            StringBuilder response = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) 
            {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            // Convert StringBuilder to String
            String responseString = response.toString();

            // Step 2: Parse JSON with Jackson
            ObjectMapper objectMapper = new ObjectMapper();

            Course course = objectMapper.readValue(responseString, Course.class);

            CSC200 csc200 = course.getCSC200();

            return csc200;

        }
        catch (Exception e) 
        {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args)
    {
        CSC200 csc200 = APIRequest();

        System.out.println("Section A Total Students: " + csc200.getSectionA().getTotalStudents());
        
    }
    
}
