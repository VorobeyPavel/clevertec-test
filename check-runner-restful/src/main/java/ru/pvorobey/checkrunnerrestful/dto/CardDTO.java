package ru.pvorobey.checkrunnerrestful.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardDTO {

    private Long id;
    private String number;
    private Integer discount;
}
