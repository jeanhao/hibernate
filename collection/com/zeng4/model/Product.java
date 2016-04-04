package com.zeng4.model;

import java.util.Map;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.MapKey;
import javax.persistence.MapKeyColumn;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
@Entity
@Table(name = "t_product")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	@ElementCollection
	@JoinTable(name = "t_specs_prize",joinColumns = @JoinColumn(name = "product_id"))
	@Column(name = "prize")//对应规格的价格
	@MapKeyColumn(name = "specs")
	private Map<String, Double> prize;
//	@ElementCollection(fetch = FetchType.LAZY)
//	@JoinTable(name = "t_product_images",joinColumns = @JoinColumn(name = "proId"))
//	@Column(name = "imagesUrl")
//	private Set<String> images ;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public Set<String> getImages() {
//		return images;
//	}
//	public void setImages(Set<String> images) {
//		this.images = images;
//	}
	public Map<String, Double> getPrize() {
		return prize;
	}
	public void setPrize(Map<String, Double> prize) {
		this.prize = prize;
	}
	
}
