package com.jpcomponents;

public enum ProgrameNameComponents {
    FRECKLE("Freckle"),
    FRECKLE_FLUENCY("Freckle Fluency"),
    LEXIA("Lexia"),
    LEXIA_ENGLISH("Lexia English"),
    LIGHT_SAIL("LightSail"),
    ST_MATH("STMath");

    private String programe_name = null;

    public String getProgrameName() {
        return programe_name;
    }
    ProgrameNameComponents(String name) {
        programe_name = name;
    }
}
