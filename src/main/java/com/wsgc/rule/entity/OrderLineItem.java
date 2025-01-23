package com.wsgc.rule.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "order_line_item")
@Data
public class OrderLineItem implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    @Column(name = "order_ID")
    private Integer orderId;

    @Column(name = "order_line_ID")
    private Integer orderLineId;

    @Column(name = "item_ID")
    private Integer itemId;

    @Column(name = "inventory_type")
    private String inventoryType;

    @Column(name = "productStatus")
    private String productStatus;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "batch_nbr")
    private String batchNbr;

    @Column(name = "order_line_status")
    private String orderLineStatus;

    @Column(name = "required_qty")
    private Integer requiredQty;

    @Column(name = "allocated_qty")
    private Integer allocatedQty;

    @Column(name = "picked_qty")
    private Integer pickedQty;

    @Column(name = "packed_qty")
    private Integer packedQty;
}

