
package com.example.proyectomammon.resources.cuenta_api;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Balance {

    @SerializedName("available")
    @Expose
    private int available;
    @SerializedName("current")
    @Expose
    private int current;
    @SerializedName("limit")
    @Expose
    private int limit;

    /**
     * No args constructor for use in serialization
     *
     */
    public Balance() {
        super();
        this.available = 0;
        this.current = 0;
        this.limit = 0;
    }

    /**
     *
     * @param current
     * @param available
     * @param limit
     */
    public Balance(int available, int current, int limit) {
        super();
        this.available = available;
        this.current = current;
        this.limit = limit;
    }

    /**
     *
     * @param balance
     */
    public Balance(Balance balance) {
        super();
        this.available = balance.getAvailable();
        this.current = balance.getCurrent();
        this.limit = balance.getLimit();
    }

    public int getAvailable() {
        return available;
    }

    public void setAvailable(int available) {
        this.available = available;
    }

    public int getCurrent() {
        return current;
    }

    public void setCurrent(int current) {
        this.current = current;
    }

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

}
