package com.paramita.poll.service.impl;

import com.paramita.poll.entity.Option;
import com.paramita.poll.entity.Poll;
import com.paramita.poll.mapper.PollMapper;
import com.paramita.poll.repository.PollRepository;
import com.paramita.poll.to.PollTO;
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
    public void voteByOption(int pollID,String option){
       Poll poll=pollRepository.findById(Long.valueOf(String.valueOf(pollID))).orElseThrow(()-> new RuntimeException("No record found with id:" + pollID));
//        List<Option> opt = poll.getOptions().stream().map(a -> {
////             Option o = new Option();
////             o=a;
//            if (a.getOptionValue().equalsIgnoreCase(option)) {
//                a.setVotes(a.getVotes() + 1);
//            }
//            return a;
//        }).toList();
        for(Option o:poll.getOptions()) {
            if (o.getOptionValue().equalsIgnoreCase(option)) {
                o.setVotes(o.getVotes() + 1);
                break;
            }
        }
       Poll pollResult = pollRepository.save(poll);

    }
}
