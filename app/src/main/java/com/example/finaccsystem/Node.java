package com.example.finaccsystem;

public class Node {

    private String name; // название
    private String amount;  // баланс
    private String currency; //валюта
    private int iconResource; // ресурс флага

    public Node(String name, String amount, String currency, int icon){

        this.name=name;
        this.amount=amount;
        this.currency=currency;
        this.iconResource=icon;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return this.amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency(){
         return this.currency;
    }

    public void setCurrency(String currency){
        this.currency=currency;
    }
    public int getIconResource() {
        return this.iconResource;
    }

    public void setIconResource(int iconResource) {
        this.iconResource = iconResource;
    }
}
