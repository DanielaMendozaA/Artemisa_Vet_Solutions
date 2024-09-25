package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.CategoryServicePort;
import com.riwi.artemisa.domain.models.CategoryModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.CategoryCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.CategoryResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.CategoryResponseAdmin;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.CategoryRestMapper;
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
@RequestMapping("category")
public class CategoryController {

    private final CategoryServicePort servicePort;
    private final CategoryRestMapper restMapper;

    //Controllers admin------------------------

    @PostMapping("/admin/create")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Create a new category",
            description = "Creates a new category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<CategoryResponseAdmin> save(@RequestBody CategoryCreateRequest request){
        CategoryModel savedCategory = servicePort.save(restMapper.toCategory(request));
        return ResponseEntity.status(HttpStatus.CREATED).body(restMapper.toCategoryResponseAdmin(savedCategory));
    }

    @PutMapping("admin/update/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Update a category",
            description = "Update a category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category update successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<CategoryResponseAdmin> update(
            @RequestBody CategoryCreateRequest request,@PathVariable String name){
        //mapeamos el request a un modelo
        CategoryModel categoryModel = restMapper.toCategory(request);
        //llamo al servicio
        CategoryModel updateCategory = servicePort.update(name,categoryModel);
        //mapeo el nuevo servicio a un responseAdmin
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toCategoryResponseAdmin(updateCategory));
    }

    @GetMapping("admin/read/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Read a category",
            description = "read a category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<CategoryResponseAdmin> readByName(@PathVariable String name){
        CategoryModel categoryModel = servicePort.readByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toCategoryResponseAdmin(categoryModel));
    }

    @GetMapping("admin/readAll")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Read All category",
            description = "read All category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<CategoryResponseAdmin>> findAll() {
        List<CategoryModel> categoryList = servicePort.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toCategoryResponseAdminList(categoryList));
    }

    @DeleteMapping("admin/delete/{name}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete a category",
            description = "Delete a category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deleted completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<?> deleteByName(@PathVariable String name){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicePort.updateStatusProduct(name));
    }

    //Controllers user------------------------

    @GetMapping("user/read/{name}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TUTOR')")
    @Operation(summary = "Read a category",
            description = "read a category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<CategoryResponse> readByNameUser(@PathVariable String name){
        CategoryModel categoryModel = servicePort.readByName(name);
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toCategoryResponse(categoryModel));
    }

    @GetMapping("user/readAll")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TUTOR')")
    @Operation(summary = "Read All category",
            description = "read All category in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Query completed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<CategoryResponse>> readAllByName(){
        List<CategoryModel> categoryList = servicePort.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(restMapper.toCategoryResponseList(categoryList));
    }

}

