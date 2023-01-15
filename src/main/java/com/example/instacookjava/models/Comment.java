package com.example.instacookjava.models;

import javax.persistence.*;
import java.sql.Date;

@Entity
public class Comment {

    public Comment() {
    }

    public Comment(String text, Date postDate) {
        this.text = text;
        this.postDate = postDate;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentId;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "recipe_id")
    private Recipe recipe;

    private String text;
    private Date postDate;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }
}
