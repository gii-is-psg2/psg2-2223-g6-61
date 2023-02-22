package org.springframework.samples.petclinic.hotel;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.samples.petclinic.model.BaseEntity;
import org.springframework.samples.petclinic.pet.Pet;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "bookings")
@Getter
@Setter
public class Booking extends BaseEntity {

	/**
	 * Holds value of property check in date.
	 */
	@Column(name = "check_in")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate checkInDate;

	/**
	 * Holds value of property check out date.
	 */
	@Column(name = "check_out")        
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	private LocalDate checkOutDate;
	
	/**
	 * Holds value of property pet.
	 */
	@ManyToOne
	@JoinColumn(name = "pet_id")
	private Pet pet;
	
}
