package es.satec.angolatelecom.sigo.sim.stock.service.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "SIGO_SIM_SERIAL_NUMBERS")
public class SIMSerialNumbers implements Serializable {

	private static final long serialVersionUID = -7577679007047085312L;
	
	//TODO las aserciones de spring de tamaños de campos no coinciden con los ficheros de gemalto enviados
	
	@Id
	@NotNull 
//	@Size(min=15, max=15)
	@Column(name = "Batch")		
	private String batch;
	@NotNull 
//	@Size(min=35, max=35)
	@Column(name = "Serial_Number")		
	private String serialNumber;
	
	public SIMSerialNumbers() {
		super();
	}
	
	public SIMSerialNumbers(String batch, String serialNumber) {
		super();
//		this.id = id;
		this.batch = batch;
		this.serialNumber = serialNumber;
	}

//	public Integer getId() {
//		return id;
//	}
//
//	public void setId(Integer id) {
//		this.id = id;
//	}

	public String getBatch() {
		return batch;
	}

	public void setBatch(String batch) {
		this.batch = batch;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	@Override
	public String toString() {
		return "SIMSerialNumbers [Batch=" + batch + ", Serial_Number=" + serialNumber +"]";
	}		
}
