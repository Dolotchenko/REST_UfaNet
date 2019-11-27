package org.ufanet.services;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement
public class CustomNotes {

    @XmlElement
    List<Note> noteList;

    public CustomNotes(List<Note> noteList) {
        this.noteList = noteList;
    }

    public CustomNotes() {
    }


    public List<Note> getNoteList() {
        return noteList;
    }

    public void setNoteList(List<Note> noteList) {
        this.noteList = noteList;
    }
}
