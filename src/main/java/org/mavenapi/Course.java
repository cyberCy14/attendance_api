package org.mavenapi;

/*
 * Since the API request fetches a nested JSON object, the same principle will be applied here
 * 
 * Course is course names, which includes the object csc200 from Class CSC200
 * 
 * CSC200 will contain the sections A, B, and C respectively, which will be classes (SectionA, SectionB, SectionC)
 * NOTE: you can change the variable names if you like
 * 
 * The Sections (SectionA, SectionB, SectionC) classes will contain (int) total students and (Data) Data
 * 
 * Class Data wll then include, (int) id, (string) name, (Attendance) attendance
 * 
 * Attendance will contain (bool) is_present and (string) date
 */

import com.fasterxml.jackson.annotation.*;

public class Course {
    
    @JsonProperty("csc200")
    private CSC200 csc200;


    //default no argument constructor, important
    public Course()
    {

    }

    @JsonGetter("csc200")
    public CSC200 getcsc200()
    {
        return this.csc200;
    }

}
