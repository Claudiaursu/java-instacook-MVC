package com.example.instacookjava.models;
import javax.persistence.*;
import java.sql.Date;

@Entity
public class Contest {
    public Contest() {
    }

    public Contest(String contestTitle, Date startDate, Date endDate, boolean isActive, Integer winPoints) {
        this.contestTitle = contestTitle;
        this.startDate = startDate;
        this.endDate = endDate;
        this.isActive = isActive;
        this.winPoints = winPoints;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int contestId;

    private String contestTitle;

    private Date startDate;

    private Date endDate;

    private boolean isActive;

    private Integer winPoints;

    private String winner;

    @ManyToOne
    @JoinColumn(name = "kitchen_id")
    private Kitchen kitchen;

    public String getContestTitle() {
        return contestTitle;
    }

    public void setContestTitle(String contestTitle) {
        this.contestTitle = contestTitle;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public Integer getWinPoints() {
        return winPoints;
    }

    public void setWinPoints(Integer winPoints) {
        this.winPoints = winPoints;
    }

    public String getWinner() {
        return winner;
    }

    public void setWinner(String winner) {
        this.winner = winner;
    }

    public Kitchen getKitchen() {
        return kitchen;
    }

    public void setKitchen(Kitchen kitchen) {
        this.kitchen = kitchen;
    }

    public int getContestId() {
        return contestId;
    }

    public void setContestId(int contestId) {
        this.contestId = contestId;
    }
}
