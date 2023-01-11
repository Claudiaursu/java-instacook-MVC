package com.example.instacookjava.models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Bucatarie {
    public Bucatarie() {
    }

    public Bucatarie(String numeBucatarie, String descriereBucatarie, String regiune) {
        this.numeBucatarie = numeBucatarie;
        this.descriereBucatarie = descriereBucatarie;
        this.regiune = regiune;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idBucatarie;

    private String numeBucatarie;

    private String descriereBucatarie;

    private String regiune;

    @OneToMany(mappedBy = "bucatarie")
    private List<Reteta> retete = new ArrayList<>();

    @OneToMany(mappedBy = "bucatarie")
    private List<Concurs> concursuri = new ArrayList<>();

    public String getNumeBucatarie() {
        return numeBucatarie;
    }

    public void setNumeBucatarie(String numeBucatarie) {
        this.numeBucatarie = numeBucatarie;
    }

    public String getDescriereBucatarie() {
        return descriereBucatarie;
    }

    public void setDescriereBucatarie(String descriereBucatarie) {
        this.descriereBucatarie = descriereBucatarie;
    }

    public String getRegiune() {
        return regiune;
    }

    public void setRegiune(String regiune) {
        this.regiune = regiune;
    }

    public List<Reteta> getRetete() {
        return retete;
    }

    public void setRetete(List<Reteta> retete) {
        this.retete = retete;
    }

    public List<Concurs> getConcursuri() {
        return concursuri;
    }

    public void setConcursuri(List<Concurs> concursuri) {
        this.concursuri = concursuri;
    }
}
