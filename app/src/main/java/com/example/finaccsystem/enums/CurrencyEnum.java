package com.example.finaccsystem.enums;

import java.util.ArrayList;

public enum CurrencyEnum {
    DOLLAR_USA(1, "Доллар США", "$"),
    RUBLE_RU(2, "Российский Рубль", "₽"),
    ETHEREUM(3, "Эфир", "ETH");

    private final int id;
    private final String name;
    public final String symbol;

    CurrencyEnum(int id, String name, String symbol) {
        this.id = id;
        this.name = name;
        this.symbol = symbol;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getSymbol() {
        return symbol;
    }

    public static String getCurrencySymbolById(int id) {
        for (CurrencyEnum cur : values()) {
            if (cur.getId() == id) {
                return cur.symbol;
            }
        }
        return null;
    }

    public static int getCurrencyIdBySymbol(String symbol) {
        for (CurrencyEnum cur : values()) {
            if (cur.symbol == symbol)
                return cur.getId();
        }
        return 0;
    }

    public static ArrayList<String> getAllCurrencySymbols(){
        ArrayList<String> listOfCurrencySymbols = new ArrayList<>();
        for(CurrencyEnum item: CurrencyEnum.values()){
            listOfCurrencySymbols.add(CurrencyEnum.valueOf(String.valueOf(item)).getSymbol());
        }
        return listOfCurrencySymbols;
    }
}