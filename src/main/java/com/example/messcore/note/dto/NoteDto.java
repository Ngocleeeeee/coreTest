package com.example.messcore.note.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NoteDto {
    private String title;
    private String content;
    private UUID staffId;
    private String fullName;
    private UUID conversationId;
}

