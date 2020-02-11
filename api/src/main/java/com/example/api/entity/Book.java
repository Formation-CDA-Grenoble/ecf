package com.example.api.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.example.api.entity.*;

@Entity
@Table(name = "book")
@EntityListeners(AuditingEntityListener.class)
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private long id;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "summary", nullable = false)
    private String summary;

    @Column(name = "published_at", nullable = true)
    private Date publishedAt;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private Set<Chapter> chapters;

    @ManyToMany
    @JoinTable(name = "books_in_tags",
        joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id"))    
    @JsonIgnoreProperties("books")
    private Set<Tag> tags;


    public Date getPublishedAt() {
    	return this.publishedAt;
    }
    public void setPublishedAt(Date publishedAt) {
    	this.publishedAt = publishedAt;
    }

    public Set<Tag> getTags() {
    	return this.tags;
    }
    public void setTags(Set<Tag> tags) {
    	this.tags = tags;
    }

    public Set<Chapter> getChapters() {
    	return this.chapters;
    }
    public void setChapters(Set<Chapter> chapters) {
    	this.chapters = chapters;
    }

    public String getSummary() {
    	return this.summary;
    }
    public void setSummary(String summary) {
    	this.summary = summary;
    }

    public String getTitle() {
    	return this.title;
    }
    public void setTitle(String title) {
    	this.title = title;
    }

    public long getId() {
    	return this.id;
    }
    public void setId(long id) {
    	this.id = id;
    }


}
