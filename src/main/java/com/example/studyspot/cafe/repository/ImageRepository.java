package com.example.studyspot.cafe.repository;

import com.example.studyspot.cafe.domain.model.Cafe;
import com.example.studyspot.cafe.domain.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    List<Image> findByCafe(Cafe cafe);
}
