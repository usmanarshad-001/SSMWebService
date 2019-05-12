package com.management.ssm.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="TBLPRODUCT")
public class Product {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="PRODUCT_ID")
	private long PRODUCT_ID;
	
	@Column(name="PRODUCT_NAME")
	private String PRODUCT_NAME;
	
	@Column(name="PRODUCT_STOCK")
	private double PRODUCT_STOCK;
	
	@Column(name="PRODUCTPURCHASE_PRICE")
	private double PRODUCTPURCHASE_PRICE;
	
	@Column(name="PRODUCTSALE_PRICE")
	private double PRODUCTSALE_PRICE;
	
	@JoinColumn(name="PRODUCTTYPE_ID")
	@ManyToOne(cascade= {CascadeType.ALL}, targetEntity=ProductType.class)
//	@Column(name="PRODUCTTYPE_ID")
	private ProductType PRODUCTTYPE_ID;

	@JsonIgnore
	@Column(name="PRODUCTMODIFIED_BY")
	private String PRODUCTMODIFIED_BY;
	
	@JsonIgnore
	@Column(name="PRODUCTMODIFIED_WHEN")
	private String PRODUCTMODIFIED_WHEN;
	
	@JsonIgnore
	@Column(name="PRODUCTMODIFIED_WORKSTATION")
	private String PRODUCTMODIFIED_WORKSTATION;
	
	@Column(name="PRODUCTIS_ACTIVE")
	private char PRODUCTIS_ACTIVE;

	public long getPRODUCT_ID() {
		return PRODUCT_ID;
	}

	public void setPRODUCT_ID(long pRODUCT_ID) {
		PRODUCT_ID = pRODUCT_ID;
	}

	public String getPRODUCT_NAME() {
		return PRODUCT_NAME;
	}

	public void setPRODUCT_NAME(String pRODUCT_NAME) {
		PRODUCT_NAME = pRODUCT_NAME;
	}

	public double getPRODUCT_STOCK() {
		return PRODUCT_STOCK;
	}

	public void setPRODUCT_STOCK(double pRODUCT_STOCK) {
		PRODUCT_STOCK = pRODUCT_STOCK;
	}

	public double getPRODUCTPURCHASE_PRICE() {
		return PRODUCTPURCHASE_PRICE;
	}

	public void setPRODUCTPURCHASE_PRICE(double pRODUCTPURCHASE_PRICE) {
		PRODUCTPURCHASE_PRICE = pRODUCTPURCHASE_PRICE;
	}

	public double getPRODUCTSALE_PRICE() {
		return PRODUCTSALE_PRICE;
	}

	public void setPRODUCTSALE_PRICE(double pRODUCTSALE_PRICE) {
		PRODUCTSALE_PRICE = pRODUCTSALE_PRICE;
	}

	public ProductType getPRODUCTTYPE_ID() {
		return PRODUCTTYPE_ID;
	}

	public void setPRODUCTTYPE_ID(ProductType pRODUCTTYPE_ID) {
		PRODUCTTYPE_ID = pRODUCTTYPE_ID;
	}

	public String getPRODUCTMODIFIED_BY() {
		return PRODUCTMODIFIED_BY;
	}

	public void setPRODUCTMODIFIED_BY(String pRODUCTMODIFIED_BY) {
		PRODUCTMODIFIED_BY = pRODUCTMODIFIED_BY;
	}

	public String getPRODUCTMODIFIED_WHEN() {
		return PRODUCTMODIFIED_WHEN;
	}

	public void setPRODUCTMODIFIED_WHEN(String pRODUCTMODIFIED_WHEN) {
		PRODUCTMODIFIED_WHEN = pRODUCTMODIFIED_WHEN;
	}

	public String getPRODUCTMODIFIED_WORKSTATION() {
		return PRODUCTMODIFIED_WORKSTATION;
	}

	public void setPRODUCTMODIFIED_WORKSTATION(String pRODUCTMODIFIED_WORKSTATION) {
		PRODUCTMODIFIED_WORKSTATION = pRODUCTMODIFIED_WORKSTATION;
	}

	public char getPRODUCTIS_ACTIVE() {
		return PRODUCTIS_ACTIVE;
	}

	public void setPRODUCTIS_ACTIVE(char pRODUCTIS_ACTIVE) {
		PRODUCTIS_ACTIVE = pRODUCTIS_ACTIVE;
	}
	
}
