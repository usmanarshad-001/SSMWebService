package com.management.ssm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TBLORDERSTATUS")
public class OrderStatus {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ORDERSTATUS_ID;

    @Column(name = "ORDERSTATUS_NAME")
    private String ORDERSTATUS_NAME;

    @OneToMany(mappedBy = "ORDERORDERSTATUS_ID", cascade = CascadeType.ALL)
    private Set<Order> orders= new HashSet<Order>();

    public long getORDERSTATUS_ID() {
        return ORDERSTATUS_ID;
    }

    public void setORDERSTATUS_ID(long oRDERSTATUS_ID) {
        ORDERSTATUS_ID = oRDERSTATUS_ID;
    }

    public String getORDERSTATUS_NAME() {
        return ORDERSTATUS_NAME;
    }

    public void setORDERSTATUS_NAME(String oRDERSTATUS_NAME) {
        ORDERSTATUS_NAME = oRDERSTATUS_NAME;
    }

    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }

}
