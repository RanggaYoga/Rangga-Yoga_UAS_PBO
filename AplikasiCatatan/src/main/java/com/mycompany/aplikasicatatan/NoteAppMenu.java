/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.aplikasicatatan;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Rangga Yoga
 */
public class NoteAppMenu {
    private NoteService noteService;
    private Scanner scanner;

    public NoteAppMenu(String databasePath) {
        noteService = new NoteService(new DatabaseStorage(databasePath));
        scanner = new Scanner(System.in);
    }
    
    public void start(){
        displayMainMenu();
        addNote();
        showNotes();
    }
    
    public static String PilihanMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Pilih nomor menu (1 - 4) : ");
        String input = scanner.nextLine();
        return input;
    }
    
    private void displayMainMenu(){
        boolean boole = true;
            while(boole){
                System.out.println(" ");
                System.out.println("Aplikasi menu Catatan Oleh Rangga Yoga Budi Setyawan NIM : 23201006");
                System.out.println("1. Tambahkan Catatan");
                System.out.println("2. Tampilkan Catatan");
                System.out.println("3. Hapus Catatan");
                System.out.println("4. Keluar");
                System.out.println(" ");
                String pilihan = PilihanMenu();

                switch (pilihan){
                case "1":
                    addNote();
                    break;
                case "2":
                    showNotes();
                    break;
                case "3":
                    deleteNote();
                    break;
                case "4":
                    boole = false;
                    break;
                default:
                    System.out.println("Pilihan tidak ada.");  
            }
        }
    }
    
    private void addNote() {
        System.out.println("");
        System.out.println("Anda akan menambahkan data catatan");
        System.out.print("Masukkan Catatan: ");
        String catatanbaru = scanner.nextLine();
        noteService.createNote(catatanbaru);
        System.out.println("Catatan berhasil disimpan: " + catatanbaru);
    }
    
    private void showNotes() {
        List<String> notes = noteService.readNotes();
        System.out.println("");
        System.out.println("Catatan tersimpan:");
        if (notes.isEmpty()) {
            System.out.println("Tidak ada catatan ditemukan.");
        } else {
            int n = 0;
            for (String catatan : notes) {
                n++;
                System.out.println(n + ". " + catatan);
            }
        }
    }
    
    private void deleteNote(){
        List<String> dataCatatan = noteService.readNotes();
        System.out.println("");
        System.out.println("Anda akan menghapus data catatan");
        System.out.println("List data catatan : ");
        if(dataCatatan.isEmpty()){
            System.out.println("Data catatan kosong");
        }else{
            int n = 0;
            for(String catatan : dataCatatan){
                n++;
                System.out.println(n + ". " + catatan);
            }
            
            System.out.println("");
            int catatan = noteService.getNoteCount();
            System.out.print("Pilih catatan nomor berapa yang akan dihapus (1-" + catatan + ") : ");
            int idx = scanner.nextInt();
            if(idx > catatan){
                System.out.println("Kelebihan");
            }else{
                String catatanDipilih = noteService.getNoteByIndex(idx-1);
                noteService.deleteNote(catatanDipilih);
                System.out.println("Catatan (" + catatanDipilih + ") berhasil dihapus.");
            }
        }
    }
}
