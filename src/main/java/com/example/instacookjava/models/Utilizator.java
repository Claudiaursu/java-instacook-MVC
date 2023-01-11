package com.example.instacookjava.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Utilizator {
    public Utilizator(){
    }

    public Utilizator(String nume, String prenume, String email, String taraOrigine, String telefon, Integer totalPuncte) {
        this.nume = nume;
        this.prenume = prenume;
        this.email = email;
        this.taraOrigine = taraOrigine;
        this.telefon = telefon;
        this.totalPuncte = totalPuncte;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUtilizator;
    private String nume;
    private String prenume;
    private String email;
    private String taraOrigine;
    private String telefon;
    private Integer totalPuncte;

    @OneToMany(mappedBy = "utilizator")
    private List<Colectie> colectii = new ArrayList<>();

    @OneToMany(mappedBy = "utilizator")
    private List<Comentariu> comentarii = new ArrayList<>();

    @ManyToMany(mappedBy = "reactii")
    private List<Reteta> reactiiRetete = new ArrayList<>();

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTaraOrigine() {
        return taraOrigine;
    }

    public void setTaraOrigine(String taraOrigine) {
        this.taraOrigine = taraOrigine;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Integer getTotalPuncte() {
        return totalPuncte;
    }

    public void setTotalPuncte(Integer totalPuncte) {
        this.totalPuncte = totalPuncte;
    }

    public List<Colectie> getColectii() {
        return colectii;
    }

    public void setColectii(List<Colectie> colectii) {
        this.colectii = colectii;
    }

    public List<Comentariu> getComentarii() {
        return comentarii;
    }

    public void setComentarii(List<Comentariu> comentarii) {
        this.comentarii = comentarii;
    }

    public List<Reteta> getReactiiRetete() {
        return reactiiRetete;
    }

    public void setReactiiRetete(List<Reteta> reactiiRetete) {
        this.reactiiRetete = reactiiRetete;
    }
}
