package com.danielpm1982.Maven_Web_Spring_JPA_AOP_NoXML_Security_REST_WS.entity;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="entity_model")
public class EntityModel {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private long id;
	@Column(name="date_time_epoch")
	private long dateTimeEpochInSeconds;
	@Column(name="date_time_stringified")
	private String dateTimeStringified;
	@NotNull(message="Description cannot be empty! Type something!")
	@Column(name="description")
	private String entityDescription;
	public EntityModel() {
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public long getDateTimeEpochInSeconds() {
		return dateTimeEpochInSeconds;
	}
	public void setDateTimeEpochInSeconds(long dateTimeEpochInSeconds) {
		this.dateTimeEpochInSeconds = dateTimeEpochInSeconds;
	}
	public String getDateTimeStringified() {
		return dateTimeStringified;
	}
	public void setDateTimeStringified(String dateTimeStringified) {
		this.dateTimeStringified = dateTimeStringified;
	}
	public String getEntityDescription() {
		return entityDescription;
	}
	public void setEntityDescription(String entityDescription) {
		this.entityDescription = entityDescription;
	}
	@Override
	public String toString() {
		return "Id: "+id+" Description: "+entityDescription+" DateTimeEpochInSeconds: "+dateTimeEpochInSeconds+" DateTimeStringified: "+dateTimeStringified;
	}
}
