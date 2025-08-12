package com.p2p.ecomm.model;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class StoreDetails implements Serializable {
    private Long storeId;
    private String name;
}
