package org.mavenapi;

import com.fasterxml.jackson.annotation.*;

//please see explanation at Course.java
public class AttendanceA {
    
    @JsonProperty("is_present")
    private boolean isPresent;
    
    @JsonProperty("date")
    private String date;

    //default no argument constructor, important
    public AttendanceA()
    {

    }

    @JsonGetter("is_present")
    public boolean isPresent()
    {
        //It SHOULD have been getIsPresent, but it sounds redundant -John
        return this.isPresent;
    }

    @JsonGetter("date")
    public String getDate()
    {
        return this.date;
    }

<<<<<<< HEAD
}
=======
}
>>>>>>> 885b4f0199ec035e0874319eeb6311f7466715b2
