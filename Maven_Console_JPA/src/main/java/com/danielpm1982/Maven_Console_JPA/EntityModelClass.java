package com.danielpm1982.Maven_Console_JPA;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="Entity_Model")
public class EntityModelClass {
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id=-1;
	@Column(name="name")
	private String name;
	public EntityModelClass() {
	}
	public EntityModelClass(String name) {
		this.name=name;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return this.getClass().getSimpleName()+" id = "+id+" name = "+name;
	}
}
