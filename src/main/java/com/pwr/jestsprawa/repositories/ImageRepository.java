package com.pwr.jestsprawa.repositories;

import com.pwr.jestsprawa.model.Image;
import com.pwr.jestsprawa.model.Issue;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Integer> {
    List<Image> findAllByIssue_Id(int issueId);
}
