package com.danielpm1982.vaccinesRecord.dao;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.danielpm1982.vaccinesRecord.entity.VaccineAdministration;

@Repository
public class VaccineAdministrationDAO implements VaccineAdministrationDAOInterface{
	private VaccineAdministration vaccineAdministration;
	private EntityManager entityManager;
	@Autowired
	public VaccineAdministrationDAO(EntityManager entityManager) {
		this.entityManager=entityManager;
	}
	@Override
	public void administerVaccineOnPatient(long patientId, long vaccineId, String administrator, String place, LocalDate localDate, LocalTime localTime) {
		Session session = entityManager.unwrap(Session.class);
		vaccineAdministration = new VaccineAdministration();
		vaccineAdministration.setPatientId(patientId);
		vaccineAdministration.setVaccineId(vaccineId);
		if(localDate==null) {
			vaccineAdministration.setLocalDateTime(LocalDateTime.now());
		} else {
			LocalDateTime localDateTime = null;
			if(localTime!=null) {
				localDateTime = LocalDateTime.of(localDate, localTime);
			} else {
				localDateTime = LocalDateTime.of(localDate, LocalTime.parse("00:00:00"));
			}
			vaccineAdministration.setLocalDateTime(localDateTime);
		}
		vaccineAdministration.setAdministrator(administrator);
		vaccineAdministration.setPlace(place);
		vaccineAdministration.setAdministeredSuccessfully(true);
		session.saveOrUpdate(vaccineAdministration);
	}
	@Override
	public List<VaccineAdministration> getVaccineAdministrationList() {
		Session session = entityManager.unwrap(Session.class);
		return session.createQuery("from VaccineAdministration", VaccineAdministration.class).getResultList();
	}
}
