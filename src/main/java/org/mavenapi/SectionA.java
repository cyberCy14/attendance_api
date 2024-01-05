package org.mavenapi;
<<<<<<< HEAD
import java.util.List;

public class SectionA {

    private String sectionName;
    private List<Student> students;

    public SectionA(String sectionName, List<Student> students) {
        this.sectionName = sectionName;
        this.students = students;
    }

    public String getSectionName() {
        return sectionName;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> studentsList) {
    }

    public void setSectionName(String sectionName2) {
    }
=======

import java.util.List;

import com.fasterxml.jackson.annotation.*;

//please see explanation at Course.java
// contains data for "section-a"
public class SectionA {
    
    @JsonProperty("total_students")
    private int totalStudents;

    @JsonProperty("data")
    private List<DataA> dataA;
    //List is recommended but not necessary, it is just what I used -John

    //default no argument constructor, important
    public SectionA()
    {

    }

    @JsonGetter("total_students")
    public int getTotalStudents()
    {
        return this.totalStudents;
    }

    @JsonGetter("data")
    public List<DataA> getDataA()
    {
        return this.dataA;
    }

>>>>>>> 885b4f0199ec035e0874319eeb6311f7466715b2
}
