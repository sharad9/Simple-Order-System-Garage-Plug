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
public class Customer {

    @Id
    @Column
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private long id;

    @Column
    @NotNull
    private String name;

    @Column
    @NotNull
    private String email;

    @Column
    @NotNull
    private Category category = Category.regular;

    @OneToMany
    private Set<CustomerOrder> allOrders = new HashSet<>();

    @Column
    @NotNull
    private boolean active = true;
}


