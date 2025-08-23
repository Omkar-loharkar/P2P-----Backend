package com.p2p.ecomm.model.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Entity
@Table(name = "delivery_details")
@Getter
@Setter
public class DeliveryDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String deliveryId;

    private String deliveryType;

    private String deliveryInstructions;

    private long deliverOnOrBefore;

    private long deliveryCharge;

    private double totalDeliveryCharge;

    private String store;

    private String method;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "addressId")
    private Address address;

    private String storeLocationIdentifier;
}
