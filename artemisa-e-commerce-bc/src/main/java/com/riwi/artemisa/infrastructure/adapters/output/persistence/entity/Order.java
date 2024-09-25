package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;


import com.riwi.artemisa.utils.enums.StatesOrder;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "purchase_orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "id_user", nullable = false)
    private String idUser;

    @Column(name = "order_date", nullable = false)
    private LocalDate orderDate;

    @Column(name = "total_order")
    private Float totalOrder = 0.0f;

    @Enumerated(EnumType.STRING)
    private StatesOrder statesOrder;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<OrderDetails> orderDetails;

}