package com.wsgc.rule.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table (name = "location")
@Data
public class Location implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer Id;

    @Column(name = "location_barcode")
    private Integer locationBarcode;

    @Column(name = "location_class")
    private String locationClass;

    @Column(name = "is_temp")
    private String isTemp;

    @Column(name = "location_uom")
    private String locationUom;

    @Column(name = "location_min")
    private Integer locationMin;

    @Column(name = "location_max")
    private Integer locationMax;
}
