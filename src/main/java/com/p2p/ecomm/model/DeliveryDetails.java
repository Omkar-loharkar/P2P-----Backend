package com.p2p.ecomm.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Map;

@Getter
@Setter
public class DeliveryDetails implements Serializable {
    private String deliveryId;
    private String deliveryType;
    private String deliveryInstructions;
    private long deliverOnOrBefore;
    private long deliveryCharge;
    private double totalDeliveryCharge;
    private String store;
    private String method;
    private Address address;
    private Map<String, String> properties;
    private String storeLocationIdentifier;
}
