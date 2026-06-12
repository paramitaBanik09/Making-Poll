package com.paramita.poll.to;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class VoteByOptionResponseTO {
    private int pollId;
    private String option;
    private Long vote;
}
