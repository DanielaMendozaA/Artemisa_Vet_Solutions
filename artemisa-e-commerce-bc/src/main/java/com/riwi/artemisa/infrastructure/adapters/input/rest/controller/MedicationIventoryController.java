package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.MedicationInventoryServicePort;
import com.riwi.artemisa.domain.models.MedicationInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.MedicationInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationInventoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.MedicationInventoryResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.MedicationInventoryRestMapper;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("medicationInventory")
@RequiredArgsConstructor
public class MedicationIventoryController {

    private final MedicationInventoryServicePort servicePort;
    private final MedicationInventoryRestMapper restMapper;

    //Admin controllers ----------------------

    @PostMapping("admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a inventory medication",
            description = "create a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create medication inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<MedicationInventoryResponseAdmin> save(@RequestBody MedicationInventoryCreateRequest request){
        MedicationInventoryModel inventoryModel = servicePort.save(restMapper.toMedicationInventroy(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(restMapper.toMedicationInventoryAdmin(inventoryModel));
    }

    @PutMapping("admin/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a inventory medication",
            description = "Update a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update medication inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public ResponseEntity<MedicationInventoryResponseAdmin> update(
            @RequestBody MedicationInventoryCreateRequest request,@PathVariable Long id){
        MedicationInventoryModel inventoryModel = servicePort.update(id, restMapper.toMedicationInventroy(request));
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationInventoryAdmin(inventoryModel));
    }

    @DeleteMapping("admin/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a inventory medication",
            description = "Delete a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete medication inventory successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<String> delete(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicePort.updateStatusProduct(id));
    }

    @GetMapping("admin/readAll")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Read all inventory medications",
            description = "Read all inventory medications in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read all medication inventories successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required"),
            @ApiResponse(responseCode = "403", description = "Forbidden - User access required")
    })
    public ResponseEntity<List<MedicationInventoryResponseAdmin>> getAllAdmin(){
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationInventoryResponseAdminList(servicePort.findAll()));
    }

    @GetMapping("admin/read/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Read a inventory medication",
            description = "Read a inventory medication by id in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read medication inventory successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required"),
            @ApiResponse(responseCode = "403", description = "Forbidden - User access required")
    })
    public ResponseEntity<MedicationInventoryResponseAdmin> getMedicationInventory(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationInventoryAdmin(servicePort.readById(id)));
    }

    @GetMapping("admin/readAllCategory/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Read all inventory medications by category",
            description = "Read all inventory medications by category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read all medication inventories successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public ResponseEntity<List<MedicationInventoryResponseAdmin>> getAllByCategory(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationInventoryResponseAdminList(servicePort.readAllCategory(id)));
    }

    @GetMapping("admin/readAllProductName")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Read all product name",
            description = "read all product name in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<MedicationInventoryResponseAdmin>> getAllByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationInventoryResponseAdminList(servicePort.findAllByName(name)));
    }

    @GetMapping("admin/readAllProductStock")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Read all Stock",
            description = "read all stock in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<MedicationInventoryResponseAdmin>> getAllByStock(@RequestParam int stock){
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationInventoryResponseAdminList(servicePort.readAllProductStock(stock)));
    }

    //User Controllers -----------------------

    @GetMapping("user/readAll")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TUTOR')")
    @Operation(summary = "Read all inventory medication",
            description = "read all inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<MedicationInventoryResponse>> getAll(){
           return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationResponseList(servicePort.readAllIfAvailable()));
    }

    @GetMapping("user/readAvailable")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TUTOR')")
    @Operation(summary = "Read all available inventory medication",
            description = "read all available inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<MedicationInventoryResponse>> getAllAvailable(){
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toMedicationResponseList(servicePort.readAllIfAvailable()));
    }
}
