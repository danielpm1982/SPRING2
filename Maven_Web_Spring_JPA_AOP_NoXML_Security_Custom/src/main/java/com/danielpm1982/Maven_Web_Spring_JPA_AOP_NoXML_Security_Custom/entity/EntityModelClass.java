package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_Custom.entity;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import javax.validation.constraints.NotNull;

public class EntityModelClass {
	private long id;
	private LocalDateTime dateTime;
	private String dateTimeStringified;
	@NotNull(message="Description cannot be empty! Type something!")
	private String entityDescription;
	public EntityModelClass() {
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
