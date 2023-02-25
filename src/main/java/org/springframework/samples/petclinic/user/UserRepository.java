package org.springframework.samples.petclinic.user;

import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends  CrudRepository<User, String>{
	void deleteById(int id);
}
