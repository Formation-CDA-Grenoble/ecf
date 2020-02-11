package com.example.api.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import com.example.api.entity.Chapter;
import com.example.api.repository.ChapterRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/chapters")
@CrossOrigin
public class ChapterController {
    @Autowired
    private ChapterRepository chapterRepository;

    @GetMapping("")
    public List<Chapter> getAllBook() {
        return chapterRepository.findAllByOrderByPublishedAtDesc();
    }

    @GetMapping("/{id}")
    public Chapter getChapterById(@PathVariable(value = "id") Long id) {
        return chapterRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chapter not found")
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteChapterById(@PathVariable(value = "id") Long id) {
        chapterRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chapter not found")
        );
        chapterRepository.deleteById(id);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Chapter createChapter(@Valid @RequestBody Chapter chapter) {
        chapter.setPublishedAt(new Date());
        return chapterRepository.save(chapter);
    }

    @PatchMapping("/{id}")
    public Chapter updatePartialChapter(@PathVariable(value = "id") Long id, @Valid @RequestBody Chapter newChapter) {
        Chapter chapter = chapterRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chapter not found")
        );
        if (newChapter.getTitle() != null) {
            chapter.setTitle(newChapter.getTitle());
        }
        if (newChapter.getContent() != null) {
            chapter.setContent(newChapter.getContent());
        }
        if (newChapter.getPublishedAt() != null) {
            chapter.setPublishedAt(newChapter.getPublishedAt());
        }
        if (newChapter.getBook() != null) {
            chapter.setBook(newChapter.getBook());
        }
        return chapterRepository.save(chapter);
    }

    @PutMapping("/{id}")
    public Chapter updateChapter(@PathVariable(value = "id") Long id, @Valid @RequestBody Chapter newChapter) {
        Chapter chapter = chapterRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Chapter not found")
        );
        chapter.setTitle(newChapter.getTitle());
        chapter.setContent(newChapter.getContent());
        chapter.setPublishedAt(newChapter.getPublishedAt());
        chapter.setBook(newChapter.getBook());
        return chapterRepository.save(chapter);
    }
}
