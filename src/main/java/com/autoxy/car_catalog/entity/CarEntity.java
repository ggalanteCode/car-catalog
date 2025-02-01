package com.autoxy.car_catalog.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class CarEntity {

    @Id
    private Long id;

    @Column
    private String brand;

    @Column
    private String model;

    @Column
    private String yearOfProduction;

    @Column
    private Double price;

    @Column
    private String status;

}
