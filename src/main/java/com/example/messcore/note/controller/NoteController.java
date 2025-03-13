package com.example.messcore.note.controller;

import com.example.messcore.note.dto.NoteDto;
import com.example.messcore.note.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {
    private final NoteService noteService;

    @PostMapping
    public void createNote(@RequestBody NoteDto noteDto) {
        noteService.saveNote(noteDto);
    }

}
