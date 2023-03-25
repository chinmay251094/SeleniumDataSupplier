package com.jpcomponents;

public enum Districts {
    ROCKETSHIP_PUBLIC_SCHOOLS("Rocketship Public Schools"),
    RED_BRIDGE("Redbridge"),
    ROMERO_ACADEMY_AT_RESURRECTION("Romero Academy at Resurrection"),
    KIPP_DC("KIPP DC"),
    ACE_CHARTER_SCHOOLS("Ace Charter Schools");

    private String District = null;

    public String getDistricts() {
        return District;
    }

    Districts(String s) {
        District = s;
    }
}
