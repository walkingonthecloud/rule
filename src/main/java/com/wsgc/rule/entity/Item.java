package com.wsgc.rule.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "item")
@Data
public class Item implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    @Column(name = "item_ID")
    private Integer itemId;

    @Column(name = "item_desc")
    private String itemDesc;

    @Column(name = "inventory_type")
    private String inventoryType;

    @Column(name = "product_status")
    private String shipToAddress1;

    @Column(name = "country_of_origin")
    private String countryOfOrigin;

    @Column(name = "batch_nbr")
    private String batchNBr;

    @Column(name = "sellable")
    private String sellable;

}
