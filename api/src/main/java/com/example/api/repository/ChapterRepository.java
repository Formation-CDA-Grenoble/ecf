package com.example.api.repository;

import java.util.List;

import com.example.api.entity.Chapter;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChapterRepository extends JpaRepository<Chapter, Long> {
    public List<Chapter> findAllByOrderByPublishedAtDesc();
}
