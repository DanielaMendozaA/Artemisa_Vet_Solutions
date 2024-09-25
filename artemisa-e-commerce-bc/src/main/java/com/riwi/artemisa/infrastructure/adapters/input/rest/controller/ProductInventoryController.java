package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.ProductInventoryServicePort;
import com.riwi.artemisa.domain.models.ProductInventoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.ProductInventoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductInventoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.ProductResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.ProductInventoryRestMapper;
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
@RequestMapping("productInventory")
@RequiredArgsConstructor
public class ProductInventoryController {

    private final ProductInventoryServicePort servicePort;
    private final ProductInventoryRestMapper mapper;

    //Admin

    @PostMapping("admin/save")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a inventory product",
            description = "create a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "create product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<ProductResponseAdmin> save (@RequestBody ProductInventoryCreateRequest productInventoryCreateRequest){
        ProductInventoryModel model =  servicePort.save(mapper.toProductInventory(productInventoryCreateRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toProductInventoryResponseAmin(model));
    }

    @PutMapping("admin/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "update a inventory product",
            description = "update a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<ProductResponseAdmin> update(@RequestBody ProductInventoryCreateRequest productInventoryUpdateRequest,@PathVariable Long id){
        ProductInventoryModel inventoryModel = servicePort.update(id,mapper.toProductInventory(productInventoryUpdateRequest));
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toProductInventoryResponseAmin(inventoryModel));
    }


    @DeleteMapping("admin/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "delete a inventory product",
            description = "delete a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "delete product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<String> updateStatusProduct(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicePort.updateStatusProduct(id));
    }

    @GetMapping("admin/readAll")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "read all inventory products",
            description = "read all inventory medications in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read all product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<ProductResponseAdmin>> readAll(){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toProductInventoryResponseAdminList(servicePort.findAll()));
    }

    @GetMapping("admin/read/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "read a inventory product by id",
            description = "read a inventory medication by id in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<ProductResponseAdmin> readById(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toProductInventoryResponseAmin(servicePort.readById(id)));
    }

    @GetMapping("admin/readAllCategory/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "read all inventory products by category",
            description = "read all inventory medications by category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read all product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<ProductResponseAdmin>> readAllByCategory(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toProductInventoryResponseAdminList(servicePort.readAllCategory(id)));
    }

    @GetMapping("admin/readAllProductName")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Read all inventory product by name",
            description = "read all inventory product by name in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory by name successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<ProductResponseAdmin>> readAllByName(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toProductInventoryResponseAdminList(servicePort.findAllByName(name)));
    }

    @GetMapping("admin/updateStock")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "update stock of a inventory product",
            description = "update stock of a inventory medication in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "update stock product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public ResponseEntity<String> updateStock(@RequestParam int stock, @RequestParam Long id){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicePort.updateStock(stock, id));
    }

    @GetMapping("admin/readAllProductsByStock")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "read all inventory products by stock",
            description = "read all inventory medications by stock in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory by stock successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public ResponseEntity<List<ProductResponseAdmin>> readAllByStock(@RequestParam Integer stock){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toProductInventoryResponseAdminList(servicePort.readAllProductStock(stock)));
    }

    //Users

    @GetMapping("user/readById/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TUTOR')")
    @Operation(summary = "read a inventory product by id",
            description = "read a inventory medication by id in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public ResponseEntity<ProductInventoryResponse> readByIdByuser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toProductInventoryResponse(servicePort.readById(id)));
    }

    @GetMapping("user/readAllProductName")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TUTOR')")
    @Operation(summary = "read all inventory product by name",
            description = "read all inventory medication by name in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public ResponseEntity<List<ProductInventoryResponse>> readAllByNameByUser(@RequestParam String name){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toProductResponseList(servicePort.findAllByName(name)));
    }

    @GetMapping("user/readAllCategory/{id}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TUTOR')")
    @Operation(summary = "read all inventory products by category",
            description = "read all inventory medications by category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public ResponseEntity<List<ProductInventoryResponse>> readAllByCategoryByUser(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toProductResponseList(servicePort.readAllCategory(id)));
    }

    @GetMapping("user/readAllProductAvailable")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TUTOR')")
    @Operation(summary = "read all inventory products available",
            description = "read all inventory medications available in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "read product inventory successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - User access required")
    })
    public ResponseEntity<List<ProductInventoryResponse>> readAllAviable(){
        return ResponseEntity.status(HttpStatus.OK).body(mapper.toProductResponseList(servicePort.readAllIfAvailable()));

    }

}
