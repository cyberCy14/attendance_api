package org.mavenapi;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class FetchOneA {
    public static Student[] fetchDataForStudent(String searchQuery) {

        try {
            String apiUrl = "http://127.0.0.1:8000/api/student/search?search=" + searchQuery;

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
                Student[] students = parseStudents(jsonString);

                for (Student student : students) {
                    System.out.println(student);
                }
            } else {
                System.out.println("Failed to fetch data. Response code: " + responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new Student[0];
    }



    private static Student[] parseStudents(String jsonString) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonData = objectMapper.readTree(jsonString);
            JsonNode csc200 = jsonData.get("csc200");

            if (csc200 != null) {
                JsonNode data = csc200.get("data");
                if (data != null) {
                    JsonNode sectionA = data.get("Section A");
                    if (sectionA != null && sectionA.isArray()) {
                        Student[] students = new Student[sectionA.size()];

                        for (int i = 0; i < sectionA.size(); i++) {
                            JsonNode studentData = sectionA.get(i);
                            int id = studentData.get("id").asInt();
                            String name = studentData.get("name").asText();
                            JsonNode attendanceArray = studentData.get("attendance");

                            List<Attendance> attendanceList = new ArrayList<>();

                            for (JsonNode attendanceData : attendanceArray) {
                                boolean isPresent = attendanceData.get("is_present").asBoolean();
                                String date = attendanceData.get("date").asText();

                                Attendance attendance = new Attendance();
                                attendance.setPresent(isPresent);
                                attendance.setDate(date);

                                attendanceList.add(attendance);
                            }

                            Student student = new Student();
                            student.setID(id);
                            student.setName(name);
                            student.setAttendance(attendanceList);

                            students[i] = student;
                        }

                        return students;
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Student[0];
    }


        public static void main(String [] args){
          String searchQuery = "Radoc";
        Student[] students = fetchDataForStudent(searchQuery);

        for (Student student : students) {
            System.out.println(student);
        }
    }

//     private static Student[] fetchDataForStudent(String searchQuery) {
//         return null;
//     }
}

class Student {
    private int id;
    private String name;
    private List<Attendance> attendance;

    public void setID(int id) {
        this.id = id;
    }

    public int getID() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setAttendance(List<Attendance> attendance) {
        this.attendance = attendance;
    }

    public List<Attendance> getAttendance() {
        return attendance;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder("\nStudent\n");
        result.append("\tid = ").append(id).append("\n");
        result.append("\tname = ").append(name).append("\n");

        result.append("\tattendance = [");
        for (Attendance attendanceItem : attendance) {
            result.append(attendanceItem).append(", ");
        }
        result.append("]\n");

        return result.toString();
    }
}

class Attendance {
    private boolean isPresent;
    private String date;

    public void setPresent(boolean isPresent) {
        this.isPresent = isPresent;
    }

    public boolean isPresent() {
        return isPresent;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "\n\tAttendance\n" +
                "\t\tis_present = " + isPresent +
                "\n\t\tdate = " + date;
    }


}
