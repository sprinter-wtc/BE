package com.example.studyspot.review.repository;

import com.example.studyspot.review.domain.model.Review;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class MemoryReviewRepository implements ReviewRepository{

    private static Map<Long,Review> store = new HashMap<>();
    private static long uniqueId = 0; //Id 발급기

    @Override
    public Review save(Review review) {
        long id = uniqueId++;
        review.setId(id);
        store.put(review.getId(),review);
        return review;
    }

    @Override
    public Optional<Review> findById(Long reviewId) {
        return Optional.ofNullable(store.get(reviewId));
    }

    @Override
    public List<Review> findAllByCafeId(Long cafeId) {

        return store.values().stream()
                .filter(review->review.getCafeId().equals(cafeId))
                .collect(Collectors.toList());
    }

    @Override
    public void delete(Review review){
        store.remove(review.getId());
    };

    @Override
    public void clear(){
        store.clear();
    }
}
