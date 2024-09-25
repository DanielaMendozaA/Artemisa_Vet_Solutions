package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.*;


@Entity
@Table(name = "order_details")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetails {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "quantity", nullable = false)
    private int quantity;

    @Column(name = "unit_price", nullable = false)
    private Float unitPrice;

    @Column(name = "total_price_product", nullable = false)
    private Float totalPriceProduct;

    @ManyToOne
    @JoinColumn(name = "products_id")
    private ProductInventory product;

    @ManyToOne
    @JoinColumn(name = "medication_id")
    private MedicationInventory medication;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

}

