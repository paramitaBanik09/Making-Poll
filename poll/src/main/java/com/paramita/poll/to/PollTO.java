package com.paramita.poll.to;

import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
public class PollTO {
    private int id;
    private String question;
    private List<OptionTO> options;
}
