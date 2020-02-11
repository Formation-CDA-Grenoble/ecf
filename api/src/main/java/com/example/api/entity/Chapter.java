package com.example.api.entity;

import java.util.Date;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.api.entity.*;

@Entity
@Table(name = "chapter")
@EntityListeners(AuditingEntityListener.class)
public class Chapter {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", nullable = false)
    private String content;

    @Column(name = "published_at", nullable = true)
    private Date publishedAt;

    @ManyToOne
    @JsonIgnoreProperties("chapters")
    private Book book;


    public Book getBook() {
    	return this.book;
    }
    public void setBook(Book book) {
    	this.book = book;
    }

    public Date getPublishedAt() {
    	return this.publishedAt;
    }
    public void setPublishedAt(Date publishedAt) {
    	this.publishedAt = publishedAt;
    }

    public String getTitle() {
    	return this.title;
    }    
    public void setTitle(String title) {
    	this.title = title;
    }    

    public String getContent() {
    	return this.content;
    }
    public void setContent(String content) {
    	this.content = content;
    }

    public long getId() {
    	return this.id;
    }    
    public void setId(long id) {
    	this.id = id;
    }    

}
