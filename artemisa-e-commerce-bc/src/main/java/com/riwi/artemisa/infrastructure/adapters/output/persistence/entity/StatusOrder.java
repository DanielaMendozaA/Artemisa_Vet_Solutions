package com.riwi.artemisa.infrastructure.adapters.output.persistence.entity;



import com.riwi.artemisa.utils.enums.StatesOrder;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "status_order")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StatusOrder {

    @Id
    @GeneratedValue( strategy = GenerationType.IDENTITY)
    private long id;

    @Enumerated()
    private StatesOrder state;

//    private List<Order> purchase_order;
}