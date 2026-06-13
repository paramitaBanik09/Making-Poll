package com.paramita.poll.to;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OptionTO {
    @NotBlank(message = "Option should not be blank")
    private String option;
    @Min(value = 0, message = "No of vote should not be negative")
    private Long votes;
}
