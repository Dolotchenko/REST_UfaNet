package org.ufanet.services;

public class Note {
    private int id;
    private String textNote;

    public Note(int id, String textNote) {
        this.id = id;
        this.textNote = textNote;
    }

    public Note() {
    }

    public int getId() {
        return id;
    }

    public String getTextNote() {
        return textNote;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTextNote(String textNote) {
        this.textNote = textNote;
    }
}
