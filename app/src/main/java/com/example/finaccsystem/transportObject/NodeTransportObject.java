package com.example.finaccsystem.transportObject;

import java.math.BigDecimal;

public class NodeTransportObject {
    private String id;
    private String name;
    private String description;
    private int currencyId;
    private BigDecimal amount;
    private String userId;
    private boolean isExternal;

    public NodeTransportObject() {
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public int getCurrencyId() {
        return currencyId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getUserId() {
        return userId;
    }

    public boolean isExternal() {
        return isExternal;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCurrencyId(int currencyId) {
        this.currencyId = currencyId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setExternal(boolean external) {
        isExternal = external;
    }
}
