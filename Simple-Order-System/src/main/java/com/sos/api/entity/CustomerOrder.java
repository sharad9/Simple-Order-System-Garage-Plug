package com.sos.api.entity;

import com.sos.api.enums.Category;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data

@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrder {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private double discount = 0.0;

    @Column
    @NotNull
    private long customerId;

    @Column
    @NotNull
    private boolean active = true;
}


