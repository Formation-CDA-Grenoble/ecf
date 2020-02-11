package com.example.api.controller;

import java.util.List;

import javax.validation.Valid;

import com.example.api.entity.Tag;
import com.example.api.repository.TagRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("/api/tags")
@CrossOrigin
public class TagController {
    @Autowired
    private TagRepository tagRepository;

    @GetMapping("")
    public List<Tag> getAllBooks() {
        return tagRepository.findAll();
    }

    @GetMapping("/{id}")
    public Tag getTagById(@PathVariable(value = "id") Long id) {
        return tagRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found")
        );
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    public void deleteTagById(@PathVariable(value = "id") Long id) {
        tagRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found")
        );
        tagRepository.deleteById(id);
    }

    @PostMapping("")
    @ResponseStatus(value = HttpStatus.CREATED)
    public Tag createTag(@Valid @RequestBody Tag tag) {
        return tagRepository.save(tag);
    }

    @PatchMapping("/{id}")
    public Tag updatePartialTag(@PathVariable(value = "id") Long id, @Valid @RequestBody Tag newTag) {
        Tag tag = tagRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found")
        );
        if (newTag.getName() != null) {
            tag.setName(newTag.getName());
        }
        if (newTag.getBooks() != null) {
            tag.setBooks(newTag.getBooks());
        }
        return tagRepository.save(tag);
    }

    @PutMapping("/{id}")
    public Tag updateTag(@PathVariable(value = "id") Long id, @Valid @RequestBody Tag newTag) {
        Tag tag = tagRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tag not found")
        );
        tag.setName(newTag.getName());
        tag.setBooks(newTag.getBooks());
        return tagRepository.save(tag);
    }
}
