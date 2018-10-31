package com.apap.tutorial7.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * 
 * @author faisalridwan
 * DealerModel
 */
@Entity
@Table(name = "dealer")
public class DealerModel implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Size(max = 50)
	@Column(name = "alamat", nullable = false)
	private String alamat;
	
	@NotNull
	@Size(max = 13)
	@Column(name = "no_telp", nullable = false)
	private String noTelp;
	
	@OneToMany(mappedBy = "dealer", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<CarModel> listCar;
	
	public void setId(long id) {
		this.id = id;
	}
	
	public void setAlamat(String alamat) {
		this.alamat = alamat;
	}
	
	public void setNoTelp(String noTelp) {
		this.noTelp = noTelp;
	}
	
	public void setListCar(List<CarModel> listCar) {
		this.listCar = listCar;
	}
	
	public long getId() {
		return id;
	}
	
	public String getAlamat() {
		return alamat;
	}
	
	public String getNoTelp() {
		return noTelp;
	}
	
	public List<CarModel> getListCar() {
		try {
			int n = listCar.size(); 
	        for (int i=1; i<n; ++i) 
	        { 
	            CarModel key = listCar.get(i); 
	            int j = i-1; 
	  
	            /* Move elements of arr[0..i-1], that are 
	               greater than key, to one position ahead 
	               of their current position */
	            while (j>=0 && listCar.get(j).getPrice() < key.getPrice()) 
	            { 	
	            	listCar.set(j+1, listCar.get(j));
	                j = j-1; 
	            } 
	            listCar.set(j+1, key); 
	        }			
		} catch (Exception e) {
			// TODO: handle exception
			return listCar;
		}
		return listCar;
	}
}
