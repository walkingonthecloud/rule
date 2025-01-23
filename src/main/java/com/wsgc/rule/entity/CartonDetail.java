package com.wsgc.rule.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "carton_detail")
@Data
public class CartonDetail implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    @Column(name = "carton_detail_ID")
    private Integer cartonDetailId;

    @Column(name = "order_line_ID")
    private Integer orderLineId;

    @Column(name = "order_ID")
    private Integer orderId;

    @Column(name = "carton_detail_status")
    private String carton_detail_Status;

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

    @Column(name = "to_be_packed_qty")
    private Integer onhandQty;

    @Column(name = "packed_qty")
    private Integer allocatedQty;

}

