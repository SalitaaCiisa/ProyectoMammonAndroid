
package com.example.proyectomammon.resources.cuenta_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CuentasResourceApus {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("number")
    @Expose
    private String number;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("official_name")
    @Expose
    private String officialName;
    @SerializedName("balance")
    @Expose
    private Balance balance;
    @SerializedName("holder_id")
    @Expose
    private String holderId;
    @SerializedName("holder_name")
    @Expose
    private String holderName;
    @SerializedName("currency")
    @Expose
    private String currency;
    @SerializedName("refreshed_at")
    @Expose
    private String refreshedAt;
    @SerializedName("object")
    @Expose
    private String object;

    /**
     * No args constructor for use in serialization
     *
     */
    public CuentasResourceApus() {
        super();
        this.id = "Vacio";
        this.type = "Vacio";
        this.number = "Vacio";
        this.name = "Vacio";
        this.officialName = "Vacio";
        this.balance = new Balance();
        this.holderId = "Vacio";
        this.holderName = "Vacio";
        this.currency = "Vacio";
        this.refreshedAt = "Vacio";
        this.object = "Vacio";
    }

    /**
     *
     * @param number
     * @param holderName
     * @param balance
     * @param name
     * @param currency
     * @param refreshedAt
     * @param id
     * @param type
     * @param officialName
     * @param holderId
     * @param object
     */
    public CuentasResourceApus(String id, String type, String number, String name, String officialName, Balance balance, String holderId, String holderName, String currency, String refreshedAt, String object) {
        super();
        this.id = id;
        this.type = type;
        this.number = number;
        this.name = name;
        this.officialName = officialName;
        this.balance = balance;
        this.holderId = holderId;
        this.holderName = holderName;
        this.currency = currency;
        this.refreshedAt = refreshedAt;
        this.object = object;
    }

    /**
     *
     * @param cuentasResourceApus
     */
    public CuentasResourceApus(CuentasResourceApus cuentasResourceApus) {
        super();
        this.id = cuentasResourceApus.getId();
        this.type = cuentasResourceApus.getType();
        this.number = cuentasResourceApus.getNumber();
        this.name = cuentasResourceApus.getName();
        this.officialName = cuentasResourceApus.getOfficialName();
        this.balance = cuentasResourceApus.getBalance();
        this.holderId = cuentasResourceApus.getHolderId();
        this.holderName = cuentasResourceApus.getHolderName();
        this.currency = cuentasResourceApus.getCurrency();
        this.refreshedAt = cuentasResourceApus.getRefreshedAt();
        this.object = cuentasResourceApus.getObject();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOfficialName() {
        return officialName;
    }

    public void setOfficialName(String officialName) {
        this.officialName = officialName;
    }

    public Balance getBalance() {
        return balance;
    }

    public void setBalance(Balance balance) {
        this.balance = balance;
    }

    public String getHolderId() {
        return holderId;
    }

    public void setHolderId(String holderId) {
        this.holderId = holderId;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getRefreshedAt() {
        return refreshedAt;
    }

    public void setRefreshedAt(String refreshedAt) {
        this.refreshedAt = refreshedAt;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }

}
