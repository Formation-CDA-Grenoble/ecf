package com.example.api.entity;

import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.api.entity.*;

@Entity
@Table(name = "tag")
@EntityListeners(AuditingEntityListener.class)
public class Tag {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "name", nullable = false)
    private String name;

    @ManyToMany
    @JsonIgnoreProperties("tags")
    private Set<Book> books;


    public long getId() {
        return this.id;
    }
    public void setId(long id) {
        this.id = id;
    }
    
    public String getName() {
    	return this.name;
    }
    public void setName(String name) {
    	this.name = name;
    }
    
    public Set<Book> getBooks() {
    	return this.books;
    }
    public void setBooks(Set<Book> books) {
    	this.books = books;
    }
}
