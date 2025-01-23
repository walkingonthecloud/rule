package com.wsgc.rule.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "orders")
@Data
public class Orders implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    @Column(name = "order_ID")
    private Integer orderId;

    @Column(name = "ship_to")
    private String shipTo;

    @Column(name = "sold_to")
    private String soldTo;

    @Column(name = "ship_to_address1")
    private String shipToAddress1;

    @Column(name = "ship_to_address2")
    private String shipToAddress2;

    @Column(name = "ship_to_city")
    private String shipToCity;

    @Column(name = "ship_to_state")
    private String shipToState;

    @Column(name = "ship_to_zip")
    private String shipToZip;

    @Column(name = "ship_via")
    private String shipVia;

    @Column(name = "service_level")
    private String serviceLevel;

    @Column(name = "order_status")
    private String orderStatus;

}
