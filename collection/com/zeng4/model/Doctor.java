package com.zeng4.model;

import java.util.List;
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
import javax.persistence.OrderColumn;
import javax.persistence.Table;

import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.ListIndexBase;
@Entity
@Table(name = "t_doctor")
public class Doctor {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;
	@ElementCollection(fetch = FetchType.LAZY)
	@JoinTable(name = "t_patient",joinColumns = @JoinColumn(name = "doctor_id"))
	@Column(name = "patient_name")
	//@IndexColumn(name = "orders",base = 100)在新版本中，此注解已被废弃，推荐使用下面两个注解取代
	@OrderColumn(name = "orders")
	@ListIndexBase(100)
	private List<String> patientName ;
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
	public List<String> getPatientName() {
		return patientName;
	}
	public void setPatientName(List<String> patientName) {
		this.patientName = patientName;
	}
	
}
