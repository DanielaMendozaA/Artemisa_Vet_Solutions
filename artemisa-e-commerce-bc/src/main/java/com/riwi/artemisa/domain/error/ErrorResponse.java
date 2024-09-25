package com.riwi.artemisa.domain.error;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
@Builder
@Getter
@Setter
public class ErrorResponse {
    private String code;
    private String message;
    private List<String> details;
    private LocalDateTime timestamp;
}
