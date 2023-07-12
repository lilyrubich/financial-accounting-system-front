package com.example.finaccsystem.transportObject;

import com.google.gson.annotations.JsonAdapter;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionTransportObject {
    private String id;
    private String description;
    private String senderNodeId;
    private String senderNodeName;
    private String receiverNodeId;
    private String receiverNodeName;
    private BigDecimal senderAmount;
    private BigDecimal receiverAmount;
    private String dateTime;
    private String time;

    public TransactionTransportObject() {
    }

    public String getSenderNodeName() {
        return senderNodeName;
    }

    public String getReceiverNodeName() {
        return receiverNodeName;
    }

    public TransactionTransportObject(String id, String description, String senderNodeId, String receiverNodeId, BigDecimal senderAmount, BigDecimal receiverAmount, String dateTime) {
        this.id = id;
        this.description = description;
        this.senderNodeId = senderNodeId;
        this.receiverNodeId = receiverNodeId;
        this.senderAmount = senderAmount;
        this.receiverAmount = receiverAmount;
        this.dateTime = dateTime;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String time) {
        this.dateTime = time;
    }

    public void setSenderNodeName(String senderNodeName) {
        this.senderNodeName = senderNodeName;
    }

    public void setReceiverNodeName(String receiverNodeName) {
        this.receiverNodeName = receiverNodeName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

