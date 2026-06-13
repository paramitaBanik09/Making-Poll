package com.paramita.poll.controller;

import com.paramita.poll.service.impl.PollService;
import com.paramita.poll.to.PollTO;
import com.paramita.poll.to.VoteByOptionResponseTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/poll")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200/")
public class PollController {

    private final PollService pollService;

    @GetMapping("/")
    public ResponseEntity<List<PollTO>> getPollList() {
        return ResponseEntity.ok(pollService.getPolls());
    }
    @PostMapping("/")
    public ResponseEntity<PollTO> createPoll(@Valid @RequestBody PollTO pollTO) {
        return ResponseEntity.ok(pollService.createPoll(pollTO));
    }

    @PostMapping("{pollId}/vote")
    public ResponseEntity<VoteByOptionResponseTO> provideVoteByOption(@PathVariable int pollId, @RequestParam(name="option",required = true)String option){
        return ResponseEntity.ok(pollService.voteByOption(pollId,option));
    }
}
