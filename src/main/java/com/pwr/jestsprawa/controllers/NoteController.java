package com.pwr.jestsprawa.controllers;

import com.pwr.jestsprawa.exceptions.IssueNotFoundException;
import com.pwr.jestsprawa.exceptions.UserNotFoundException;
import com.pwr.jestsprawa.model.*;
import com.pwr.jestsprawa.repositories.IssuesRepository;
import com.pwr.jestsprawa.repositories.NoteRepository;
import com.pwr.jestsprawa.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
@CrossOrigin
public class NoteController {
    private final IssuesRepository issueRepository;
    private final NoteRepository noteRepository;
    private final UserRepository userRepository;

    @GetMapping("/notes")
    public ResponseEntity<List<NoteDto>> getNotesForIssue(@RequestParam int issueId){
        List<Note> issueNotes = noteRepository.findAllByIssueId(issueId);
        List<NoteDto> notes = issueNotes.stream().map(NoteDto::fromNote).collect(Collectors.toList());
        return ResponseEntity.ok(notes);
    }

    @PostMapping("/notes/add")
    public ResponseEntity<NoteDto> addNote(@RequestBody NoteDto newNote, Authentication authentication){

        Optional<Issue> issue = issueRepository.findIssueById(newNote.getIssue());
        Issue currentIssue = issue.orElseThrow(IssueNotFoundException::new);
        User user = userRepository.findOneByEmailIgnoreCase(authentication.getName())
                .orElseThrow(UserNotFoundException::new);
        Note note = new Note();
        note.setDescription(newNote.getDescription());
        note.setCreationDate(newNote.getCreationDate());
        note.setIssue(currentIssue);
        note.setUser(user);
        noteRepository.save(note);
        return ResponseEntity.ok(newNote);
    }
}
