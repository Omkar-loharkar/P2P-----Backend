package com.example.ecommerce.model.domain;

import com.example.ecommerce.model.entity.Product;

public class ProductDO {

    private Long id;
    private String name;
    private double price;
    private String image;
    private String description;
    private String expiryDate;
    private String mfgDate;
    private String batchNo;
    private String mrp;


    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getExpiryDate() { return expiryDate; }
    public void setExpiryDate(String expiryDate) { this.expiryDate = expiryDate; }
    public String getMfgDate() { return mfgDate; }
    public void setMfgDate(String mfgDate) { this.mfgDate = mfgDate; }
    public String getBatchNo() { return batchNo; }
    public void setBatchNo(String batchNo) { this.batchNo = batchNo; }
    public String getMrp() { return mrp; }
    public void setMrp(String mrp) { this.mrp = mrp; }

    public Product toEntity(ProductDO productDO) {
        Product prodEntity = new Product();
        prodEntity.setId(productDO.getId());
        prodEntity.setName(productDO.getName());
        prodEntity.setPrice(productDO.getPrice());
        prodEntity.setImage(productDO.getImage());
        prodEntity.setDescription(productDO.getDescription());
        prodEntity.setExpiryDate(productDO.getExpiryDate());
        prodEntity.setMfgDate(productDO.getMfgDate());
        prodEntity.setBatchNo(productDO.getBatchNo());
        prodEntity.setMrp(productDO.getMrp());
        return prodEntity;

    }
}
