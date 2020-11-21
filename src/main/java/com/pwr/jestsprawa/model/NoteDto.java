package com.pwr.jestsprawa.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NoteDto {
    private String description;
    private LocalDateTime creationDate;
    private int issue;
    private String user;

    public static NoteDto fromNote(Note note) {
        return new NoteDto(
                note.getDescription(),
                note.getCreationDate(),
                note.getIssue().getId(),
                note.getUser().getEmail()

        );
    }

}
