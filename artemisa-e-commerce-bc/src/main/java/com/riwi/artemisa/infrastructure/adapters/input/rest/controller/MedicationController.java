package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.MedicationServicePort;
import com.riwi.artemisa.domain.models.MedicationModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.MedicationRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("medication")
public class MedicationController {

    private final MedicationServicePort servicePort;
    private final MedicationRestMapper restMapper;

    //Controllers admin --------------------
    @PostMapping("admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a medication",
            description = "create a medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<MedicationResponseAdmin> save(@RequestBody MedicationCreateRequest request){
        MedicationModel saveMedication = servicePort.save(restMapper.toMedication(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(restMapper.toMedicationResponseAdmin(saveMedication));
    }

    @PutMapping("admin/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a medication",
            description = "update a medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Medication updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<MedicationResponseAdmin> update(
            @RequestBody MedicationCreateRequest request,@PathVariable Long id)
    {
        MedicationModel medicationModel = restMapper.toMedication(request);
        MedicationModel updateMedication = servicePort.update(id, medicationModel);
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationResponseAdmin(updateMedication));
    }

    @GetMapping("admin/read/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Read a medication",
            description = "read a medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<MedicationResponseAdmin> readByName(@PathVariable String name){
        MedicationModel medicationModel = servicePort.readByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationResponseAdmin(medicationModel));
    }

    @GetMapping("admin/readAll")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Read All medication",
            description = "read all medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public  ResponseEntity<List<MedicationResponseAdmin>> findAll() {
        List<MedicationModel> medicationList = servicePort.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMediaResponseAdminList(medicationList));
    }

    @DeleteMapping("admin/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a medication",
            description = "Delete a medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted medication successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<?> deleteById(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicePort.deletebyId(name));
    }

    //Controllers user------------------------
    @GetMapping("user/read/{name}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TUTOR')")
    @Operation(summary = "Read a medication",
            description = "read a medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<MedicationResponse> readByNameUser(@PathVariable String name){
        MedicationModel medicationModel = servicePort.readByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationResponse(medicationModel));
    }

    @GetMapping("user/readAll")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TUTOR')")
    @Operation(summary = "Read All medication",
            description = "read all medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<MedicationResponse>> readAllByName(){
        List<MedicationModel> medicationList = servicePort.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationResponseList(medicationList));
    }

}
