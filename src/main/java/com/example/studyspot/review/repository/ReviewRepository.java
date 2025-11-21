package com.example.studyspot.review.repository;

import com.example.studyspot.review.domain.model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long> {
    //Review save(Review review);

    //Optional<Review> findById(Long reviewId);

    List<Review> findAllByCafeId(Long cafeId);

    //public void delete(Review review);

    //public void update(Review review);

   //void clear();
}
