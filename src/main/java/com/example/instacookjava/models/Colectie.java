package com.example.instacookjava.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Colectie {

    public Colectie(){
    }

    public Colectie(String titluColectie, String descriereColectie, boolean publica, String calePoza) {
        this.titluColectie = titluColectie;
        this.descriereColectie = descriereColectie;
        this.publica = publica;
        this.calePoza = calePoza;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idColectie;
    private String titluColectie;
    private String descriereColectie;
    private boolean publica;
    private String calePoza;

    @ManyToOne
    @JoinColumn(name = "id_utilizator")
    private Utilizator utilizator;

    @OneToMany(mappedBy = "colectie")
    private List<Reteta> retete = new ArrayList<>();

    public String getTitluColectie() {
        return titluColectie;
    }

    public void setTitluColectie(String titluColectie) {
        this.titluColectie = titluColectie;
    }

    public String getDescriereColectie() {
        return descriereColectie;
    }

    public void setDescriereColectie(String descriereColectie) {
        this.descriereColectie = descriereColectie;
    }

    public boolean isPublica() {
        return publica;
    }

    public void setPublica(boolean publica) {
        this.publica = publica;
    }

    public String getCalePoza() {
        return calePoza;
    }

    public void setCalePoza(String calePoza) {
        this.calePoza = calePoza;
    }

    public Utilizator getUtilizator() {
        return utilizator;
    }

    public void setUtilizator(Utilizator utilizator) {
        this.utilizator = utilizator;
    }

    public List<Reteta> getRetete() {
        return retete;
    }

    public void setRetete(List<Reteta> retete) {
        this.retete = retete;
    }
}
