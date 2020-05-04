package com.nizam.camera.model;

public enum SequenceEnum {
    CAMERA_SEQUENCE("camera_sequence");

    private String name;

    private SequenceEnum(String name) {
        this.name = name;
    }

    public String getSequenceName() {
        return name;
    }
}
