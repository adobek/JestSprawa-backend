package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {

    List<Note> findAllByIssueId(int issueId);
}
