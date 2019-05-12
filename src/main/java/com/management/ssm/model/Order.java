package com.management.ssm.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "TBLORDER")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long ORDER_ID;

    @OneToMany(mappedBy = "SALEORDER_ID", cascade = CascadeType.ALL)
    private Set<Sale> sales = new HashSet<Sale>();

    @Column(name = "ORDER_DATE")
    private String ORDER_DATE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ORDERACCOUNT_ID", unique = true)
    private Account ORDERACCOUNT_ID;

    @JoinColumn(name = "ORDERORDERSTATUS_ID")
    @ManyToOne(targetEntity = OrderStatus.class)
    private OrderStatus ORDERORDERSTATUS_ID;

    @Column(name = "ORDERIS_ACTIVE")
    private String ORDERIS_ACTIVE;

    @JoinColumn(name = "ORDERCUSTOMER_ID")
    @ManyToOne(targetEntity = Customer.class)
    private Customer ORDERCUSTOMER_ID;

    @JsonIgnore
    @Column(name = "ORDERMODIFIED_BY")
    private String ORDERMODIFIED_BY;

    @JsonIgnore
    @Column(name = "ORDERMODIFIED_WHEN")
    private String ORDERMODIFIED_WHEN;

    @JsonIgnore
    @Column(name = "ORDERMODIFIED_WORKSTATION")
    private String ORDERMODIFIED_WORKSTATION;

    public long getORDER_ID() {
        return ORDER_ID;
    }

    public void setORDER_ID(long ORDER_ID) {
        this.ORDER_ID = ORDER_ID;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public String getORDER_DATE() {
        return ORDER_DATE;
    }

    public void setORDER_DATE(String ORDER_DATE) {
        this.ORDER_DATE = ORDER_DATE;
    }

    public Account getORDERACCOUNT_ID() {
        return ORDERACCOUNT_ID;
    }

    public void setORDERACCOUNT_ID(Account ORDERACCOUNT_ID) {
        this.ORDERACCOUNT_ID = ORDERACCOUNT_ID;
    }

    public void setORDERORDERSTATUS_ID(OrderStatus ORDERORDERSTATUS_ID) {
        this.ORDERORDERSTATUS_ID = ORDERORDERSTATUS_ID;
    }

    public String getORDERIS_ACTIVE() {
        return ORDERIS_ACTIVE;
    }

    public void setORDERIS_ACTIVE(String ORDERIS_ACTIVE) {
        this.ORDERIS_ACTIVE = ORDERIS_ACTIVE;
    }

    public String getORDERMODIFIED_BY() {
        return ORDERMODIFIED_BY;
    }

    public void setORDERMODIFIED_BY(String ORDERMODIFIED_BY) {
        this.ORDERMODIFIED_BY = ORDERMODIFIED_BY;
    }

    public String getORDERMODIFIED_WHEN() {
        return ORDERMODIFIED_WHEN;
    }

    public void setORDERMODIFIED_WHEN(String ORDERMODIFIED_WHEN) {
        this.ORDERMODIFIED_WHEN = ORDERMODIFIED_WHEN;
    }

    public String getORDERMODIFIED_WORKSTATION() {
        return ORDERMODIFIED_WORKSTATION;
    }

    public void setORDERMODIFIED_WORKSTATION(String ORDERMODIFIED_WORKSTATION) {
        this.ORDERMODIFIED_WORKSTATION = ORDERMODIFIED_WORKSTATION;
    }

    public void setORDERCUSTOMER_ID(Customer ORDERCUSTOMER_ID) {
        this.ORDERCUSTOMER_ID = ORDERCUSTOMER_ID;
    }

    @JsonIgnore
    public OrderStatus getORDERORDERSTATUS_ID() {
        return ORDERORDERSTATUS_ID;
    }
}
