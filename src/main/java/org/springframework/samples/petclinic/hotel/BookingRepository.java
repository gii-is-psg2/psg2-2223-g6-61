package org.springframework.samples.petclinic.hotel;

import java.util.List;

import org.springframework.dao.DataAccessException;
import org.springframework.data.repository.Repository;


public interface BookingRepository extends Repository<Booking, Integer> {
	
	List<Booking> findByPetId(Integer petId);
	
	Booking findById(int id) throws DataAccessException;
	
	void save(Booking booking) throws DataAccessException;

}
