package com.example.finaccsystem;

public enum CurrencyEnum {
    DOLLAR_USA(1, "Доллар США", "$"),
    RUBLE_RU(2, "Российский Рубль", "₽"),
    ETHEREUM(3, "Эфир", "ETH");

    private final int id;
    private final String name;
    private final String symbol;

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

    public static String getSymbolById(int id){
        for (CurrencyEnum cur: values()){
            if (cur.id == id) {
                return cur.symbol;
            }
        }
        return null;
    }
}
