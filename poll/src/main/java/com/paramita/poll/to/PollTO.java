package com.paramita.poll.to;

import com.paramita.poll.validation.customAnnotation.UniqueConstraintValidation;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.List;


@Data
public class PollTO {
    private int id;
    @NotBlank(message = "Question should not be blank")
    private String question;
    @NotEmpty(message = "Option should not be null")
    @Size(min = 2, message = "At least two options required")
    @UniqueConstraintValidation(message = "Option should be unique")
    private List<OptionTO> options;
}
