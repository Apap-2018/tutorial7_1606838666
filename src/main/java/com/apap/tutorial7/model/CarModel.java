package com.apap.tutorial7.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

// import org.hibernate.annotations.OnDelete;
// import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * 
 * @author faisalridwan
 * CarModel
 */
@Entity
@Table(name = "car")
public class CarModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "brand", nullable = false)
	private String brand;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "type", nullable = false)
	private String type;
	
	@NotNull
	@Column(name = "price", nullable = false)
	private Long price;
	
	@NotNull
	@Column(name = "amount", nullable = false)
	private Integer amount;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "dealer_id", referencedColumnName = "id", nullable = false)
	@OnDelete(action = OnDeleteAction.NO_ACTION)
	private DealerModel dealer;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public void setPrice(Long price) {
		this.price = price;
	}
	
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	
	public void setDealer(DealerModel dealer) {
		this.dealer = dealer;
	}
	
	public long getId() {
		return id;
	}
	
	public String getBrand() {
		return brand;
	}
	
	public String getType() {
		return type;
	}
	
	public Long getPrice() {
		return price;
	}
	
	public Integer getAmount() {
		return amount;
	}
	
	public DealerModel getDealer() {
		return dealer;
	}
}
