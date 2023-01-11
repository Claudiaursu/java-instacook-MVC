package com.example.instacookjava.models;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Comentariu {

    public Comentariu() {
    }

    public Comentariu(String text, Date dataPostare) {
        this.text = text;
        this.dataPostare = dataPostare;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idComentariu;

    @ManyToOne
    @JoinColumn(name = "id_utilizator")
    private Utilizator utilizator;

    @ManyToOne
    @JoinColumn(name = "id_reteta")
    private Reteta reteta;

    private String text;
    private Date dataPostare;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getDataPostare() {
        return dataPostare;
    }

    public void setDataPostare(Date dataPostare) {
        this.dataPostare = dataPostare;
    }


}
