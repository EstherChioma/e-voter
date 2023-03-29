package com.evoter.pollType.controller;

import com.evoter.pollType.model.PollType;
import com.evoter.pollType.dto.AddPollTypeRequest;
import com.evoter.pollType.service.PollTypeService;
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
public class PollTypeController {
    private final PollTypeService pollTypeService;

    @Autowired
    public PollTypeController(PollTypeService pollTypeService) {
        this.pollTypeService = pollTypeService;
    }

    @PostMapping("/poll-types/save")
    public ResponseEntity<PollType> addPollType(@RequestBody AddPollTypeRequest request) {
        try {
            PollType savedPollType = pollTypeService.addPollType(request);
            if (savedPollType == null) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
            return new ResponseEntity<>(savedPollType, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/poll-types")
    public ResponseEntity<List<PollType>> getAllPollTypes() {
        try {
            List<PollType> pollTypes = pollTypeService.getAllPollTypes();
            if (pollTypes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pollTypes, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/poll-types/{pollTypeId}")
    public ResponseEntity<PollType> getPollTypeById(@PathVariable("pollTypeId") Long id) {
        try {
            PollType pollType = pollTypeService.getPollTypeById(id);
            if (pollType == null) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(pollType, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/poll-types/{pollTypeId}")
    public ResponseEntity<Void> deletePollTypeById(@PathVariable("pollTypeId") Long id) {
        try {
            pollTypeService.deletePollTypeById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
