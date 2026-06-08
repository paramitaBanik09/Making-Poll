package com.paramita.poll.mapper;

import com.paramita.poll.entity.Option;
import com.paramita.poll.entity.Poll;
import com.paramita.poll.to.OptionTO;
import com.paramita.poll.to.PollTO;

public class PollMapper {
    public static PollTO convertPollToPollTO(Poll poll){
        PollTO pollTo = new PollTO();
        pollTo.setId(poll.getId());
        pollTo.setQuestion(poll.getQuestion());
        pollTo.setOptions(poll.getOptions().stream().map(PollMapper::convertOptionToOptionTO).toList());
        return pollTo;
    }
    public static Poll convertPollTOToPoll(PollTO pollTO){
        Poll poll = new Poll();
        poll.setId(pollTO.getId());
        poll.setQuestion(pollTO.getQuestion());
        poll.setOptions(pollTO.getOptions().stream().map(PollMapper::convertOptionTOToOption).toList());
        return poll;
    }
    private static Option convertOptionTOToOption(OptionTO optionTo){
        Option option = new Option();
        option.setOptionValue(optionTo.getOption());
        option.setVotes(optionTo.getVotes());
        return option;
    }
    private static OptionTO convertOptionToOptionTO(Option option){
        OptionTO optionTo = new OptionTO();
        optionTo.setOption(option.getOptionValue());
        optionTo.setVotes(option.getVotes());
        return optionTo;
    }
}
