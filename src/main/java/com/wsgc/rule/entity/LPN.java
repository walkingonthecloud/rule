package com.wsgc.rule.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "lpn")
@Data
public class LPN implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    @Column(name = "lpn_ID")
    private Integer lpnId;

    @Column(name = "lpn_status")
    private String lpnStatus;

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

    @Column(name = "onhand_qty")
    private Integer onhandQty;

    @Column(name = "allocated_qty")
    private Integer allocatedQty;

    @Column(name = "pallet_ID")
    private String palletId;

}

