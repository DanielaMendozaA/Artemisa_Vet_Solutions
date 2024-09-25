package com.riwi.artemisa.infrastructure.adapters.input.rest.controller;

import com.riwi.artemisa.application.ports.input.OrderServicePort;
import com.riwi.artemisa.domain.models.OrderModel;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.request.OrderCreateRequest;
import com.riwi.artemisa.infrastructure.adapters.input.rest.dto.response.OrderResponse;
import com.riwi.artemisa.infrastructure.adapters.input.rest.mapper.OrderRestMapper;
import com.riwi.artemisa.infrastructure.config.UserContext;
import com.riwi.artemisa.utils.enums.StatesOrder;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderServicePort servicePort;
    private final OrderRestMapper orderRestMapper;

    @Autowired
    @Lazy
    private final UserContext userContext;

    @PostMapping("/user/create")
    @PreAuthorize("hasRole('TUTOR') or hasRole('ADMIN')")
    @Operation(summary = "Create a new order",
            description = "Creates a new orders in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order created successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<OrderResponse> save(@RequestBody OrderCreateRequest orderCreateRequest){
        OrderModel orderModel = orderRestMapper.orderCreateRequestToOrderModel(orderCreateRequest);
        orderModel.setIdUser(userContext.getUserId());
        return ResponseEntity.status(HttpStatus.CREATED).body(orderRestMapper.orderModelToOrderResponse(servicePort.save(orderModel)));
    }

    @GetMapping("/admin/findByUserIdAndDate/{id}/{date}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Search a order",
            description = "search a order in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order search successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<OrderResponse> readAllByUse(@PathVariable String id, @PathVariable LocalDate date){
        return ResponseEntity.ok(orderRestMapper.orderModelToOrderResponse(servicePort.readByIdUserAndOrderDate(id, date)));
    }

    @GetMapping("/admin/findAll")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "find all order",
            description = "find all order in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "find all order successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<List<OrderResponse>> readAll(){
        return ResponseEntity.ok(orderRestMapper.orderModelToOrderResponseList(servicePort.findAll()));
    }

    @GetMapping("/admin/findbyId/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "find by id order",
            description = "find a order in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order find successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<OrderResponse> readById(@PathVariable Long id){
        return ResponseEntity.ok(orderRestMapper.orderModelToOrderResponse(servicePort.readById(id)));
    }

    @PatchMapping("/admin/updateStatusOrder/{id}/{status}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "update status order",
            description = "update status order in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "status order updated successfully"  ),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<String> updateStatusOrder(@PathVariable Long id, @PathVariable StatesOrder status){
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicePort.updateStatesOrder(id, status));
    }

    @DeleteMapping("/user/delete/{idOrder}/{idOrderDetails}")
    @PreAuthorize("hasRole('ADMIN') or hasRole('TUTOR')")
    @Operation(summary = "delete order details",
            description = "delete order details in the system.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "order details deleted successfully"  ),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "401", description = "Unauthorized - Admin access required")
    })
    public ResponseEntity<String> delete(@PathVariable Long idOrder, @PathVariable Long idOrderDetails) {
        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(servicePort.deleteOrderDetails(idOrder, idOrderDetails));
    }

}
