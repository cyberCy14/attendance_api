package org.mavenapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class FetchAll {
    public FetchAll() {
    }

    public List<Section> fetchDataForAllSections() {
        try {
            String apiUrl = "http://127.0.0.1:8000/api/all";

            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");

            int responseCode = connection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                StringBuilder sb = new StringBuilder();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                    String line;
                    while ((line = reader.readLine()) != null) {
                        sb.append(line);
                    }
                }

                String jsonString = sb.toString();
                return parseSections(jsonString); // Return the fetched data
            } else {
                System.out.println("Failed to fetch data. Response code: " + responseCode);
                return Collections.emptyList(); // Return an empty list or handle error case accordingly
            }
        } catch (IOException e) {
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list or handle error case accordingly
        }
    }

    private static List<Section> parseSections(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonData = objectMapper.readTree(jsonString);

            List<Section> sectionList = new ArrayList<>();

            // Assuming sections are nested under the "csc200" field
            JsonNode csc200Node = jsonData.get("csc200");

            if (csc200Node != null && csc200Node.isObject()) {
                Iterator<String> sectionNames = csc200Node.fieldNames();

                while (sectionNames.hasNext()) {
                    String sectionName = sectionNames.next();
                    JsonNode sectionData = csc200Node.get(sectionName);
                    JsonNode secCount = sectionData.get("total_students");
                    JsonNode data = sectionData.get("data");

                    if (data.isArray()) {
                        List<Student> studentList = new ArrayList<>();

                        for (JsonNode studentData : data) {
                            int id = studentData.path("id").asInt();
                            String name = studentData.path("name").asText();

                            JsonNode attendanceArray = studentData.path("attendance");

                            if (attendanceArray == null || !attendanceArray.isArray()) {
                                System.out.println("Invalid attendance data for student " + name + " in " + sectionName);
                                continue;
                            }

                            List<Attendance> attendanceList = new ArrayList<>();

                            for (JsonNode attendanceData : attendanceArray) {
                                boolean isPresent = attendanceData.path("is_present").asBoolean();
                                String date = attendanceData.path("date").asText();

                                Attendance attendance = new Attendance();
                                attendance.setPresent(isPresent);
                                attendance.setDate(date);

                                attendanceList.add(attendance);
                            }

                            Student student = new Student();
                            student.setID(id);
                            student.setName(name);
                            student.setAttendance(attendanceList);

                            studentList.add(student);
                        }

                        Section section = new Section();
                        section.setSectionName(sectionName);
                        section.setStudents(studentList);

                        sectionList.add(section);
                    } else {
                        System.out.println("Invalid data format for section: " + sectionName);
                    }
                }
            } else {
                System.out.println("Invalid response format. 'csc200' field not found.");
            }

            return sectionList;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return Collections.emptyList();
    }



    public static void main(String[] args) {
        FetchAll fetchAll = new FetchAll();
    
        List<Section> sections = fetchAll.fetchDataForAllSections();
    
        for (Section section : sections) {
            System.out.println("Section: " + section.getSectionName());
    
            List<Student> students = section.getStudents();
            if (students != null) {
                for (Student student : students) {
                    System.out.println(student);
                }
            } else {
                System.out.println("No students found in this section.");
            }
    
            System.out.println(); 
        }
    }
    

    private Student[] fetchDataForSection(String sectionName) {
        return null;
    }
}

class Section {
    private String sectionName;
    private List<Student> students;

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public List<Student> getStudents() {
        return students;
    }
}