package com.evoter.vote.controller;

import com.evoter.vote.model.Vote;
import com.evoter.vote.dto.AddVoteRequest;
import com.evoter.vote.service.VoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author showunmioludotun
 */
@Controller
public class VoteController {
    private final VoteService voteService;

    @Autowired
    public VoteController(VoteService voteService) {
        this.voteService = voteService;
    }

    @PostMapping("/votes/save")
    public ResponseEntity<Vote> addVote(@RequestBody AddVoteRequest request) {
        try {

            Vote savedVote = voteService.addVote(request);
            if (savedVote == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(savedVote, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/votes")
    public ResponseEntity<List<Vote>> getAllVotes() {
        try {
            List<Vote> votes = voteService.getAllVotes();
            if (votes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(votes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/votes/{voteId}")
    public ResponseEntity<Vote> getVoteById(@PathVariable("voteId") Long id) {
        try {
            Vote vote = voteService.getVoteById(id);
            if (vote == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(vote, HttpStatus.OK);
        }catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
