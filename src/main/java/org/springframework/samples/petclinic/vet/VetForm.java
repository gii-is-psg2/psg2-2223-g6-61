package org.springframework.samples.petclinic.vet;



import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VetForm {
	
	private Vet vet;
	private Specialty[] specialties;
	
	

}
