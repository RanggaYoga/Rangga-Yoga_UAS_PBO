/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplikasicatatan;
import java.util.List;
/**
 *
 * @author Rangga Yoga
 */
public class NoteService {
    private DataStorage storage;
    private List<String> notes;

    public NoteService(DataStorage storage) {
        this.storage = storage;
        this.notes = readNotes();
    }

    public void createNote(String note) {
        storage.writeData(note);
        notes.add(note);
    }

    public List<String> readNotes() {
        return storage.readData();
    }

    public void deleteNote(String note) {
        notes.remove(note);
        storage.deleteData(note);
    }

    public int getNoteCount() {
        return notes.size();
    }

    public String getNoteByIndex(int index) {
        return notes.get(index);
    }
}
