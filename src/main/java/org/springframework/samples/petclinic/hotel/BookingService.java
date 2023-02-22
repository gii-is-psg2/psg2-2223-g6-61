package org.springframework.samples.petclinic.hotel;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookingService {
	
	private BookingRepository bookingRepository;
	
	@Autowired
	public BookingService(BookingRepository bookingRepository) {
		this.bookingRepository = bookingRepository;
	}
	
	@Transactional
	public Collection<Booking> findBookingByPetId(int petId) {
		return bookingRepository.findByPetId(petId);
	}
	
	@Transactional
	public void save(Booking booking) throws DataAccessException {
		bookingRepository.save(booking);
	}

	@Transactional(readOnly = true)
	public Booking findBookingById(int id) throws DataAccessException {
		return bookingRepository.findById(id);
	}
	
}
