package com.paramita.poll.entity;

import jakarta.persistence.Embeddable;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Data
public class Option {
    private String optionValue;
    private Long votes;
}
