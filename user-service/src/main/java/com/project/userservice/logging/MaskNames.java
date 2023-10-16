package com.project.userservice.logging;

public enum MaskNames {
    CLIENT_SECRET("client_secret");

    private String name;

    MaskNames(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static boolean contains(String test) {

        for (MaskNames c : MaskNames.values()) {
            if (c.name().equalsIgnoreCase((test))) {
                return true;
            }
        }

        return false;
    }
}
