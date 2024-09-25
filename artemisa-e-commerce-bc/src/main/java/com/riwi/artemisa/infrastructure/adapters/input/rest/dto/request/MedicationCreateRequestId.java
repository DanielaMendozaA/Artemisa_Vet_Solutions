package com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class MedicationCreateRequestId {

    private  Long id;
}
