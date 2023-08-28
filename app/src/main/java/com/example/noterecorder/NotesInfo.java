package com.example.noterecorder;

public class NotesInfo {

    public String name;
    public int id;
    private double turkceNet;
    private double matNet;
    private double fenNet;
    private double sosNet;

    public NotesInfo(String name, int id, double turkceNet, double matNet, double fenNet, double sosNet) {
        this.name = name;
        this.id = id;
        this.turkceNet = turkceNet;
        this.matNet = matNet;
        this.fenNet = fenNet;
        this.sosNet = sosNet;
    }
}
