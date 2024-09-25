package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import jakarta.persistence.*;
import lombok.*;


import java.time.LocalDate;


@Entity
@Table(name = "petshop_payments")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PetshopPayments {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "payment_date", nullable = false)

    private LocalDate paymentDate;


    private float amount;

    @Column(name = "payment_method", nullable = false)
    private String paymentMethod;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

}
