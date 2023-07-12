package com.example.finaccsystem.data;

public class ClientDataHolder {
    private static ClientDataHolder instance = null;
    private String auth;

    public ClientDataHolder() {
    }

    public static ClientDataHolder getInstance() {
        if (instance == null) {
            instance = new ClientDataHolder();
        }
        return instance;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public String getAuth() {
        return auth;
    }
}
