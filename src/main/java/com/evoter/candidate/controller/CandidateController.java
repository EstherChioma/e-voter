package com.evoter.candidate.controller;

import com.evoter.candidate.model.Candidate;
import com.evoter.candidate.dto.AddCandidateRequest;
import com.evoter.candidate.service.CandidateService;
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
public class CandidateController {
    private final CandidateService candidateService;

    @Autowired
    public CandidateController(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @PostMapping("/candidates/save")
    public ResponseEntity<Candidate> addCandidate(@RequestBody AddCandidateRequest request) {
        try {
            Candidate savedCandidate = candidateService.addCandidate(request);
            if (savedCandidate == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(savedCandidate, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/candidates")
    public ResponseEntity<List<Candidate>> getAllCandidates() {
        try {
            List<Candidate> candidates = candidateService.getAllCandidates();
            if (candidates.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(candidates, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/candidates/{candidateId}")
    public ResponseEntity<Candidate> getCandidateById(@PathVariable("candidateId") Long id) {
        try {
            Candidate candidate = candidateService.getCandidateById(id);
            if (candidate == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(candidate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/candidates/{candidateId}")
    public ResponseEntity<Void> deleteCandidateById(@PathVariable("candidateId") Long id) {
        try {
            candidateService.deleteCandidateById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
