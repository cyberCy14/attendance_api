package org.mavenapi;

import java.util.List;

import com.fasterxml.jackson.annotation.*;

//please see explanation at Course.java

public class DataA {
    
    @JsonProperty("id")
    private int id;

    @JsonProperty("name")
    private String name;

    @JsonProperty("attendance")
    private List<AttendanceA> attendanceA;

    //default no argument constructor, important
    public DataA()
    {

    }

<<<<<<< HEAD
    public List<InnerAttendance> getAttendanceList() {
        return null;
    }

    public int getId() {
        return 0;
    }

    public String getName() {
        return null;
    }


}
=======

}
>>>>>>> 885b4f0199ec035e0874319eeb6311f7466715b2
