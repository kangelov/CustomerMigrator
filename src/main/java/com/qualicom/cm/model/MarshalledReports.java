package com.qualicom.cm.model;

/**
 * Created by x110277 on 11/14/2016.
 */
public class MarshalledReports {

    private final String name;

    private final String fileExtension;

    private final byte[] marshalledReports;

    private final String mimeType;

    public MarshalledReports(String name, String fileExtension, byte[] marshalledReports, String mimeType) {
        this.name = name;
        this.fileExtension = fileExtension;
        this.marshalledReports = marshalledReports;
        this.mimeType = mimeType;
    }

    public String getName() {
        return name;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public byte[] getMarshalledReports() {
        return marshalledReports;
    }

    public String getMimeType() {
        return mimeType;
    }
}
