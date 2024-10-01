package com.boubyan.boubyantask.model.dto;

import com.boubyan.boubyantask.validation.EndDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor
@Data
@Builder
public class CourseDTO {
    private Long id;
    @NotBlank
    private String name;
    @NotNull
    private LocalDate courseStartDate;
    @EndDate
    private LocalDate courseEndDate;
}
