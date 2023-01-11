package com.example.instacookjava.models;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reteta {
    public Reteta() {
    }

    public Reteta(String titluReteta, String ingrediente, String instructiuni, String calePoza, String caleVideo, boolean participaConcurs) {
        this.titluReteta = titluReteta;
        this.ingrediente = ingrediente;
        this.instructiuni = instructiuni;
        this.calePoza = calePoza;
        this.caleVideo = caleVideo;
        this.participaConcurs = participaConcurs;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idReteta;

    private String titluReteta;

    private String ingrediente;

    private String instructiuni;

    private String calePoza;

    private String caleVideo;

    private boolean participaConcurs;

    @ManyToOne
    @JoinColumn(name = "id_colectie")
    private Colectie colectie;

    @ManyToOne
    @JoinColumn(name = "id_bucatarie")
    private Bucatarie bucatarie;

    @OneToMany(mappedBy = "reteta")
    private List<Comentariu> comentarii = new ArrayList<>();


    @ManyToMany
    @JoinTable(name="utilizator_reteta_reactie", joinColumns = @JoinColumn(name = "id_reteta"),
    inverseJoinColumns = @JoinColumn(name = "id_utilizator"))
    private List<Utilizator> reactii = new ArrayList<>();


    public String getTitluReteta() {
        return titluReteta;
    }

    public void setTitluReteta(String titluReteta) {
        this.titluReteta = titluReteta;
    }

    public String getIngrediente() {
        return ingrediente;
    }

    public void setIngrediente(String ingrediente) {
        this.ingrediente = ingrediente;
    }

    public String getInstructiuni() {
        return instructiuni;
    }

    public void setInstructiuni(String instructiuni) {
        this.instructiuni = instructiuni;
    }

    public String getCalePoza() {
        return calePoza;
    }

    public void setCalePoza(String calePoza) {
        this.calePoza = calePoza;
    }

    public String getCaleVideo() {
        return caleVideo;
    }

    public void setCaleVideo(String caleVideo) {
        this.caleVideo = caleVideo;
    }

    public boolean isParticipaConcurs() {
        return participaConcurs;
    }

    public void setParticipaConcurs(boolean participaConcurs) {
        this.participaConcurs = participaConcurs;
    }

    public List<Comentariu> getComentarii() {
        return comentarii;
    }

    public void setComentarii(List<Comentariu> comentarii) {
        this.comentarii = comentarii;
    }

    public List<Utilizator> getReactii() {
        return reactii;
    }

    public void setReactii(List<Utilizator> reactii) {
        this.reactii = reactii;
    }
}
