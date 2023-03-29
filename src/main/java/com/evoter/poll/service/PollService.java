package com.evoter.poll.service;

import com.evoter.poll.model.Poll;
import com.evoter.poll.repository.PollRepository;
import com.evoter.poll.dto.AddPollRequest;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author showunmioludotun
 */
@Service
public class PollService {
    private final PollRepository pollRepository;

    public PollService(PollRepository pollRepository) {
        this.pollRepository = pollRepository;
    }

    public Poll addPoll(AddPollRequest request) {
        Poll poll = new Poll();
//        poll.setPollTypeId(request.pollTypeId());
        poll.setPollDate(request.pollDate());
        return pollRepository.save(poll);
    }

    public List<Poll> getAllPolls() {
        return pollRepository.findAll();
    }

    public Poll getPollById(Long id) {
        return pollRepository.findById(id).orElse(null);
    }

    public void deletePollById(Long id) {
        pollRepository.deleteById(id);
    }

//    public List<Poll> findAllPollsWithCandidatesAndPollType() {
//        return pollRepository.findAllWithCandidatesAndPollType();
//    }
}
