package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom_AuthDB.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="entity_model")
public class EntityModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@NotNull
	@Column(name="date_time")
	private LocalDateTime dateTime;
	@Transient
	private String dateTimeStringified;
	@NotNull(message="Description cannot be empty! Type something!")
	@Column(name="description")
	private String entityDescription;
	public EntityModel() {
		dateTime = LocalDateTime.now();
		dateTimeStringified = dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL, FormatStyle.MEDIUM));
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public String getDateTimeStringified() {
		return dateTimeStringified;
	}
	public String getEntityDescription() {
		return entityDescription;
	}
	public void setEntityDescription(String entityDescription) {
		this.entityDescription = entityDescription;
	}
	@Override
	public String toString() {
		return "Id: "+id+" Description: "+entityDescription+" DateTime: "+dateTimeStringified;
	}
}
