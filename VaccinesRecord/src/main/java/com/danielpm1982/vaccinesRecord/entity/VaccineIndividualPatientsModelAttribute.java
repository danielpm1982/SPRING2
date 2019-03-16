package com.danielpm1982.vaccinesRecord.entity;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.List;
import java.util.stream.Collectors;

public class VaccineIndividualPatientsModelAttribute {
	private long vaccineId;
	private String name;
	private String tradeName;
	private String abbreviation;
	private String manufacturer;
	private String typeRoute;
	private LocalDate approvedDate;
	private String lotNumber;
	private LocalDate expirationDate;
	private String comments;
	private List<VaccineAdministration> vaccineAdministrationIndividualPatientList;
	public VaccineIndividualPatientsModelAttribute() {
	}
	public VaccineIndividualPatientsModelAttribute(Vaccine vaccine, long patientId) {
		this.vaccineId = vaccine.getVaccineId();
		this.name = vaccine.getName();
		this.tradeName = vaccine.getTradeName();
		this.abbreviation = vaccine.getAbbreviation();
		this.manufacturer = vaccine.getManufacturer();
		this.typeRoute = vaccine.getTypeRoute();
		this.approvedDate = vaccine.getApprovedDate();
		this.lotNumber = vaccine.getLotNumber();
		this.expirationDate = vaccine.getExpirationDate();
		this.comments = vaccine.getComments();
		//VaccineIndividualPatientsModelAttribute filters the vaccines from other patients and returns only that patient's vaccines.
		this.vaccineAdministrationIndividualPatientList = vaccine.getVaccineAdministration().stream().parallel().filter(x->x.getPatientId()==patientId).collect(Collectors.toList());
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
	public List<VaccineAdministration> getVaccineAdministrationIndividualPatientList() {
		return vaccineAdministrationIndividualPatientList;
	}
	public void setVaccineAdministrationIndividualPatientList(List<VaccineAdministration> vaccineAdministrationIndividualPatientList) {
		this.vaccineAdministrationIndividualPatientList = vaccineAdministrationIndividualPatientList;
	}
	@Override
	public String toString() {
		return "vaccineId :"+vaccineId+" name: "+name+" tradeName: "+tradeName+" abbreviation: "+abbreviation+" manufacturer: "+manufacturer+" typeRoute: "+typeRoute+" approvedDate: "+approvedDate.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.FULL))+" lotNumber: "+lotNumber+" expirationDate: "+expirationDate+" comments: "+comments+" vaccineAdministrationList: "+vaccineAdministrationIndividualPatientList;
	}
}
