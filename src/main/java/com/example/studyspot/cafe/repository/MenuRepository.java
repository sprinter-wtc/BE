package com.example.studyspot.cafe.repository;

import com.example.studyspot.cafe.domain.model.Cafe;
import com.example.studyspot.cafe.domain.model.Menu;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuRepository extends JpaRepository<Menu,Long> {
    List<Menu> findByCafe(Cafe cafe);
}
