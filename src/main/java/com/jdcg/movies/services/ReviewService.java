package com.jdcg.movies.services;

import com.jdcg.movies.models.Movie;
import com.jdcg.movies.models.Review;
import com.jdcg.movies.repositories.IReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {
    @Autowired
    private IReviewRepository iReviewRepository;

     @Autowired
    private MongoTemplate mongoTemplate;
    public Review createReview(String reviewBody, String imdbId){
        Review review = iReviewRepository.insert(new Review(reviewBody));

        mongoTemplate.update(Movie.class)
                .matching(Criteria.where("imdbId").is(imdbId))
                .apply(new Update().push("reviewIds").value(review))
                .first();

        return review;

    }
}
