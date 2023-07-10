package com.example.finaccsystem.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;

public class Transaction implements Serializable {
    private String id, senderNodeId, receiverNodeId,senderNodeName, receiverNodeName, description;
    private LocalDate date;
    private BigDecimal senderAmount, receiverAmount;

    public Transaction(){}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSenderNodeId() {
        return senderNodeId;
    }

    public void setSenderNodeId(String senderNodeId) {
        this.senderNodeId = senderNodeId;
    }

    public String getReceiverNodeId() {
        return receiverNodeId;
    }

    public void setReceiverNodeId(String receiverNodeId) {
        this.receiverNodeId = receiverNodeId;
    }

    public String getSenderNodeName() {
        return senderNodeName;
    }

    public void setSenderNodeName(String senderNodeName) {
        this.senderNodeName = senderNodeName;
    }

    public String getReceiverNodeName() {
        return receiverNodeName;
    }

    public void setReceiverNodeName(String receiverNodeName) {
        this.receiverNodeName = receiverNodeName;
    }

    public BigDecimal getSenderAmount() {
        return senderAmount;
    }

    public void setSenderAmount(BigDecimal senderAmount) {
        this.senderAmount = senderAmount;
    }

    public BigDecimal getReceiverAmount() {
        return receiverAmount;
    }

    public void setReceiverAmount(BigDecimal receiverAmount) {
        this.receiverAmount = receiverAmount;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
