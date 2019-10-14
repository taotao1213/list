package com.wangtao.week2.domain;

import java.io.Serializable;
import java.math.BigDecimal;

public class Goods implements Serializable{

	/**
	 * @fieldName: serialVersionUID
	 * @fieldType: long
	 * @Description: TODO
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private String name;
//	private BigDecimal price;
	private Double price;
	private String baifen;
	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", price=" + price + ", baifen=" + baifen + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public BigDecimal getPrice() {
//		return price;
//	}
//	public void setPrice(BigDecimal price) {
//		this.price = price;
//	}
	
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getBaifen() {
		return baifen;
	}
	public void setBaifen(String baifen) {
		this.baifen = baifen;
	}
	
}
