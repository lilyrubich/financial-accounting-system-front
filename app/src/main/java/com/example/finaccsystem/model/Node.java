package com.example.finaccsystem.model;

import java.io.Serializable;
import java.math.BigDecimal;

public class Node implements Serializable {

    private String name; // название
    private BigDecimal amount;  // баланс
    private String currency; //валюта
    private int iconResource; // ресурс флага

    private String id;
    private String description;
    private String userId;
    private boolean isExternal;

    public Node(String name, BigDecimal amount, String currency, int iconResource, String id, String description, String userId, boolean isExternal) {
        this.name = name;
        this.amount = amount;
        this.currency = currency;
        this.iconResource = iconResource;
        this.id = id;
        this.description = description;
        this.userId = userId;
        this.isExternal = isExternal;
    }

    public Node() {

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public int getIconResource() {
        return this.iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public void setExternal(boolean external) {
        isExternal = external;
    }

    @Override
    public String toString() {
        return name;
    }
}
