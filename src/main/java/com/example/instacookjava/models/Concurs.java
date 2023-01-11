package com.example.instacookjava.models;
import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Concurs {
    public Concurs() {
    }

    public Concurs(String titluConcurs, Date dataInceput, Date dataFinal, boolean activ, Integer puncteOferite) {
        this.titluConcurs = titluConcurs;
        this.dataInceput = dataInceput;
        this.dataFinal = dataFinal;
        this.activ = activ;
        this.puncteOferite = puncteOferite;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idConcurs;

    private String titluConcurs;

    private Date dataInceput;

    private Date dataFinal;

    private boolean activ;

    private Integer puncteOferite;

    private String castigator;

    @ManyToOne
    @JoinColumn(name = "id_bucatarie")
    private Bucatarie bucatarie;

    public String getTitluConcurs() {
        return titluConcurs;
    }

    public void setTitluConcurs(String titluConcurs) {
        this.titluConcurs = titluConcurs;
    }

    public Date getDataInceput() {
        return dataInceput;
    }

    public void setDataInceput(Date dataInceput) {
        this.dataInceput = dataInceput;
    }

    public Date getDataFinal() {
        return dataFinal;
    }

    public void setDataFinal(Date dataFinal) {
        this.dataFinal = dataFinal;
    }

    public boolean isActiv() {
        return activ;
    }

    public void setActiv(boolean activ) {
        this.activ = activ;
    }

    public Integer getPuncteOferite() {
        return puncteOferite;
    }

    public void setPuncteOferite(Integer puncteOferite) {
        this.puncteOferite = puncteOferite;
    }

    public String getCastigator() {
        return castigator;
    }

    public void setCastigator(String castigator) {
        this.castigator = castigator;
    }

    public Bucatarie getBucatarie() {
        return bucatarie;
    }

    public void setBucatarie(Bucatarie bucatarie) {
        this.bucatarie = bucatarie;
    }
}
