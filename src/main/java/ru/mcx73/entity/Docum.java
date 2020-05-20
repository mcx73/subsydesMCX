package ru.mcx73.entity;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
public class Docum {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    //private Timestamp doc_data;

    @NotBlank(message = "Пожалуйста, заполните обращение")
    @Length(max = 2048, message = "Обращение слишком длинное (более 2 кб)")
    private String text;
    @Length(max = 255, message = "Обращение слишком длинное (более 255)")

    //одному юзеру принадлежит много доков + необходимо с доком получать инфу об авторе
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private User author;

    public Docum() {
    }

    public Docum(String text, User user) {
        this.author = user;
        //this.doc_data = doc_data;
        this.text = text;

    }

    public String getAuthorName() {
        return author != null ? author.getUsername() : "<none>";
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }




}
