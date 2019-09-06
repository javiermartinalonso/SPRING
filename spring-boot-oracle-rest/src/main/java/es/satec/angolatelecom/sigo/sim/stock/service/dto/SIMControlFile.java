package es.satec.angolatelecom.sigo.sim.stock.service.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

@JsonAutoDetect
@Entity
@Table(name = "SIGO_SIM_CONTROL_FILE")
public class SIMControlFile implements Serializable {

	private static final long serialVersionUID = 4871084495124843363L;
	
	//TODO las aserciones de spring de tamaños de campos no coinciden con los ficheros de gemalto enviados
	
	@Id
	@NotNull 
//	@Size(min=15, max=15)
	@Column(name = "Batch")		
	private String batch;
	@NotNull 
//	@Size(min=35, max=35)
	@Column(name = "First_Serial_Number")		
	private String firstSerialNumber;
	@NotNull 
//	@Size(min=35, max=35)
	@Column(name = "Last_Serial_Number")	
	private String lastSerialNumber;	
	@NotNull 
	@Min(1)
	@Max(20000)
	@Column(name = "Quantity")
	private int quantity;
		
	@NotNull @Size(min=1, max=50)
	@Column(name = "FILE_Name")
	private String fileName;
	
	@NotNull	
	@Column(name = "Creation_Date")
	private Date creationDate;	
	
	@Column(name = "received")
	private Date received;
	
	@Column(name = "received_date")
	private Date receivedDate;	
			
	public SIMControlFile() {
		super();
	}

	public SIMControlFile(String batch, String firstSerialNumber, String lastSerialNumber, int quantity,
			String fileName, Date creationDate, Date received, Date receivedDate) {
		super();
//		this.id = id;		
		this.batch = batch;
		this.firstSerialNumber = firstSerialNumber;
		this.lastSerialNumber = lastSerialNumber;
		this.quantity = quantity;
		this.fileName = fileName;
		this.creationDate = creationDate;
		this.received = received;
		this.receivedDate = receivedDate;
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

	public String getFirstSerialNumber() {
		return firstSerialNumber;
	}

	public void setFirstSerialNumber(String firstSerialNumber) {
		this.firstSerialNumber = firstSerialNumber;
	}

	public String getLastSerialNumber() {
		return lastSerialNumber;
	}

	public void setLastSerialNumber(String lastSerialNumber) {
		this.lastSerialNumber = lastSerialNumber;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}

	public Date getReceived() {
		return received;
	}

	public void setReceived(Date received) {
		this.received = received;
	}

	public Date getReceivedDate() {
		return receivedDate;
	}

	public void setReceivedDate(Date receivedDate) {
		this.receivedDate = receivedDate;
	}

	@Override
	public String toString() {
		return "SIMControlFile [Batch=" + batch + ", First_Serial_Number=" + firstSerialNumber 
				+ ", Last_Serial_Number=" + lastSerialNumber + ", Quantity="
				+ quantity + ", FILE_Name=" + fileName + ", Creation_Date=" 
				+ creationDate + ", received=" + received + ", received_date=" + receivedDate +"]";
	}	
}
