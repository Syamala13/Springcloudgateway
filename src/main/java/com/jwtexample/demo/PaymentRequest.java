package com.jwtexample.demo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PaymentRequest {


    private String orderId;
    private double amount;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
