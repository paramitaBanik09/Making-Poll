package com.paramita.poll.service.impl;

import com.paramita.poll.entity.Option;
import com.paramita.poll.entity.Poll;
import com.paramita.poll.mapper.PollMapper;
import com.paramita.poll.repository.PollRepository;
import com.paramita.poll.to.PollTO;
import com.paramita.poll.to.VoteByOptionResponseTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PollService {
    private final PollRepository pollRepository;

    public List<PollTO> getPolls() {
        return pollRepository.findAll().stream().map(PollMapper::convertPollToPollTO).toList();
    }
    public PollTO createPoll(PollTO pollTO) {
        return PollMapper.convertPollToPollTO(pollRepository.save(PollMapper.convertPollTOToPoll(pollTO)));
    }
    public VoteByOptionResponseTO voteByOption(int pollID, String option){
        VoteByOptionResponseTO voteByOptionResponseTO = new VoteByOptionResponseTO();
       Poll poll=pollRepository.findById(Long.valueOf(String.valueOf(pollID))).orElseThrow(()-> new RuntimeException("No record found with id:" + pollID));
        for(Option o:poll.getOptions()) {
            if (o.getOptionValue().equalsIgnoreCase(option)) {
                o.setVotes(o.getVotes() + 1);
                propulateVoteByOptionResponse(poll.getId(),o.getOptionValue(),o.getVotes(),voteByOptionResponseTO);
                break;
            }
        }
        try {
            Poll pollResult = pollRepository.save(poll);
        }catch(Exception e){
            throw new RuntimeException(e.getMessage());
        }
        return  voteByOptionResponseTO;
    }

    private void propulateVoteByOptionResponse(int pollId, String option, Long vote, VoteByOptionResponseTO voteByOptionResponseTO){
        voteByOptionResponseTO.setPollId(pollId);
        voteByOptionResponseTO.setOption(option);
        voteByOptionResponseTO.setVote(vote);
    }
}
