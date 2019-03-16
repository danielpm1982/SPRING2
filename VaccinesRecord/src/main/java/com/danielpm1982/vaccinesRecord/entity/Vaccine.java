package com.danielpm1982.vaccinesRecord.entity;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="VACCINE")
public class Vaccine {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="Vaccine_Id")
	private long vaccineId;
	@NotNull(message = "name is required")
	@Size(min = 1, message = "name is required")
	@Column(name="NAME")
	private String name;
	@NotNull(message = "trade name is required")
	@Size(min = 1, message = "trade_name is required")
	@Column(name="TRADE_NAME")
	private String tradeName;
	@Column(name="ABBREVIATION")
	private String abbreviation;
	@NotNull(message = "manufacturer is required")
	@Size(min = 1, message = "manufacturer is required")
	@Column(name="MANUFACTURER")
	private String manufacturer;
	@NotNull(message = "type route is required")
	@Size(min = 1, message = "type_route is required")
	@Column(name="TYPE_ROUTE")
	private String typeRoute;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="APPROVED_DATE")
	private LocalDate approvedDate;
	@NotNull(message = "lot number is required")
	@Size(min = 1, message = "lot number is required")
	@Column(name="LOT_NUMBER")
	private String lotNumber;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name="EXPIRATION_DATE")
	private LocalDate expirationDate;
	@Column(name="COMMENTS")
	private String comments;
	@Lob
	private byte[] photo;
	@OneToMany(cascade=CascadeType.ALL,fetch=FetchType.EAGER, mappedBy="vaccineId")
	private List<VaccineAdministration> vaccineAdministration;
	public Vaccine() {
		vaccineAdministration = new ArrayList<VaccineAdministration>();
	}
	public long getVaccineId() {
		return vaccineId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTradeName() {
		return tradeName;
	}
	public void setTradeName(String tradeName) {
		this.tradeName = tradeName;
	}
	public String getAbbreviation() {
		return abbreviation;
	}
	public void setAbbreviation(String abbreviation) {
		this.abbreviation = abbreviation;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getTypeRoute() {
		return typeRoute;
	}
	public void setTypeRoute(String typeRoute) {
		this.typeRoute = typeRoute;
	}
	public LocalDate getApprovedDate() {
		return approvedDate;
	}
	public void setApprovedDate(LocalDate approvedDate) {
		this.approvedDate = approvedDate;
	}
	public String getLotNumber() {
		return lotNumber;
	}
	public void setLotNumber(String lotNumber) {
		this.lotNumber = lotNumber;
	}
	public LocalDate getExpirationDate() {
		return expirationDate;
	}
	public void setExpirationDate(LocalDate expirationDate) {
		this.expirationDate = expirationDate;
	}
	public String getComments() {
		return comments;
	}
	public void setComments(String comments) {
		this.comments = comments;
	}
	public List<VaccineAdministration> getVaccineAdministration() {
		return vaccineAdministration;
	}
	public void setVaccineAdministration(List<VaccineAdministration> vaccineAdministration) {
		this.vaccineAdministration = vaccineAdministration;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public String getVaccineStringWithoutClientsList() {
		return "vaccineId :"+vaccineId+" name: "+name+" tradeName: "+tradeName+" abbreviation: "+abbreviation+" manufacturer: "+manufacturer+" typeRoute: "+typeRoute+" approvedDate: "+approvedDate+" lotNumber: "+lotNumber+" expirationDate: "+expirationDate+" comments: "+comments;
	}
	@Override
	public boolean equals(Object obj) {
		return ((Vaccine)obj).getVaccineId()==this.getVaccineId();
	}
	@Override
	public String toString() {
		return "vaccineId :"+vaccineId+" name: "+name+" tradeName: "+tradeName+" abbreviation: "+abbreviation+" manufacturer: "+manufacturer+" typeRoute: "+typeRoute+" approvedDate: "+approvedDate+" lotNumber: "+lotNumber+" expirationDate: "+expirationDate+" comments: "+comments+(vaccineAdministration.isEmpty()?"":" vaccineAdministration: "+vaccineAdministration);
	}
}
