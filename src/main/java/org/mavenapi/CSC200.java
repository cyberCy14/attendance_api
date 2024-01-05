package org.mavenapi;

import com.fasterxml.jackson.annotation.JsonGetter;
<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

=======
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;

//I only want to test section-a first, delete these later if other sections are implemented
@JsonIgnoreProperties({"section-b","section-c"})
>>>>>>> 885b4f0199ec035e0874319eeb6311f7466715b2
//please see explanation at Course.java
public class CSC200 {

    //JSON Property is used so that the variable name doesnt need to be the same with the Fetched data
    @JsonProperty("section-a")
    private SectionA sectionA;

    // @JsonProperty("section-b")
    // private SectionB sectionB;
    
    // @JsonProperty("section-c")
    // private SectionC sectionC;

    //default no argument constructor, important
    public CSC200()
    {

    }

    //TODO: add other section getters and setters
    @JsonGetter("section-a")
    public SectionA getSectionA()
    {
        return this.sectionA;
    }

    @JsonSetter("section-a")
    public void setSectionA(SectionA sectionA)
    {
        this.sectionA = sectionA;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 885b4f0199ec035e0874319eeb6311f7466715b2
