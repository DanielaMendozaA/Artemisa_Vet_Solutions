package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.PetshopPaymentsServicePort;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.PetshopPaymentsRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.PetshopPaymentsResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.PetshopPaymentsRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/petshopPayments")
@RequiredArgsConstructor
public class PetshopPaymentsController {

    private final PetshopPaymentsServicePort servicePort;
    private final PetshopPaymentsRestMapper mapper;

    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Creates a new petshop payment in the system.",
            description = "Saves a new petshop payment to the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Petshop payment created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<PetshopPaymentsResponse> createPetshopPayments(@RequestBody PetshopPaymentsRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toPetshopPaymentsResponse(servicePort.save(mapper.toPetshopPaymentsModel(request))));
    }

    @GetMapping("/admin/readAll")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Retrieves all petshop payments in the system.",
            description = "Retrieves all petshop payments from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Petshop payments retrieved successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<PetshopPaymentsResponse>> readAll() {
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPetshopPaymentsResponseList(servicePort.findAll()));
    }

    @GetMapping("/admin/readByDate/{date}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Retrieves petshop payments by date in the system.",
            description = "Retrieves petshop payments by date from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Petshop payments retrieved successfully by date"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<PetshopPaymentsResponse>> readByDate(@PathVariable LocalDate date){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPetshopPaymentsResponseList(servicePort.findByDate(date)));
    }

    @GetMapping("/admin/readByUserId/{idUser}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Retrieves petshop payments by user id in the system.",
            description = "Retrieves petshop payments by user id from the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Petshop payments retrieved successfully by user id"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<PetshopPaymentsResponse>> readByPetshop(@PathVariable Long idUser){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toPetshopPaymentsResponseList(servicePort.findPetshopPaymentsByUserId(idUser)));
    }
}
