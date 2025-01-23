package com.wsgc.rule.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "carton_header")
@Data
public class CartonHeader implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    @Column(name = "carton_ID")
    private Integer cartonId;

    @Column(name = "carton_status")
    private String cartonStatus;

    @Column(name = "order_ID")
    private Integer orderId;

    @Column(name = "ship_via")
    private String shipVia;

    @Column(name = "pallet_ID")
    private String palletId;

}
