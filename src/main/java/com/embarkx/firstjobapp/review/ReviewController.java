package com.embarkx.firstjobapp.review;

import com.embarkx.firstjobapp.company.impl.CompanyServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companies/{companyId}")
public class ReviewController {

    @Autowired
    ReviewService reviewService;
    @Autowired
    private CompanyServiceImpl companyServiceImpl;

    @GetMapping("/reviews")
    public ResponseEntity<List<Review>> getAllReviews(@PathVariable Long companyId) {
        return new ResponseEntity<>(reviewService.getAllReviews(companyId), HttpStatus.OK);
    }
    @PostMapping("/reviews")
    public ResponseEntity<String> addReview(@PathVariable Long companyId, @RequestBody Review review) {
        boolean reviewSaved = reviewService.addReview(review, companyId);
        if(reviewSaved) {
            return new ResponseEntity<>("Review added", HttpStatus.CREATED);

        }
        return new ResponseEntity<>("Review not added", HttpStatus.NOT_FOUND);
        }

        @GetMapping("/reviews/{reviewId}")
        public ResponseEntity<Review> getReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
                return new ResponseEntity<>(reviewService.getReview(reviewId, companyId), HttpStatus.OK);
        }

        @PutMapping("/reviews/{reviewId}")
        public ResponseEntity<String> updateReview(@PathVariable Long companyId,@PathVariable Long reviewId, @RequestBody Review review) {
            boolean updated =  reviewService.updateReview(companyId,reviewId, review);
            if(updated) {
                return new ResponseEntity<>("Review updated", HttpStatus.OK);
            }
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }

        @DeleteMapping("reviews/{reviewId}")
    public ResponseEntity<String> deleteReview(@PathVariable Long companyId, @PathVariable Long reviewId) {
            boolean deleted = reviewService.deleteReview(companyId, reviewId);
            if(deleted) {
                return new ResponseEntity<>("Review deleted", HttpStatus.OK);
            }
            return new ResponseEntity<>("Review not found", HttpStatus.NOT_FOUND);
        }

}
