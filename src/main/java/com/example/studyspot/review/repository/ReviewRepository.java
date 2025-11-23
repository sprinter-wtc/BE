package com.example.studyspot.review.repository;

import com.example.studyspot.review.domain.model.Review;
import com.example.studyspot.review.dto.ReviewDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review,Long>, ReviewCustomRepository {
    //Review save(Review review);

    //Optional<Review> findById(Long reviewId);

    List<Review> findAllByCafeId(Long cafeId);

    @Query("""
        SELECT AVG(r.starRating)
        FROM Review r
        WHERE r.cafeId = :cafeId
    """)
    Double getAverageRatingByCafeId(Long cafeId);

    @Query("""
        SELECT COUNT(r)
        FROM Review r
        WHERE r.cafeId = :cafeId
    """)
    Long getReviewCountByCafeId(Long cafeId);

    //public void delete(Review review);

    //public void update(Review review);

   //void clear();
}
