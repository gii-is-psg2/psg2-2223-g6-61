/*
 * Copyright 2002-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.samples.petclinic.vet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.samples.petclinic.pet.PetType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @author Juergen Hoeller
 * @author Mark Fisher
 * @author Ken Krebs
 * @author Arjen Poutsma
 */
@Controller
public class VetController {

	private final VetService vetService;
    private final String VIEW_UPDATE_ADD_VET = "vets/createOrUpdateVetForm";

	@Autowired
	public VetController(VetService clinicService) {
		this.vetService = clinicService;
	}

	@GetMapping(value = { "/vets" })
	public String showVetList(Map<String, Object> model) {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for Object-Xml mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		model.put("vets", vets);
		return "vets/vetList";
	}

	@GetMapping(value = "/vets/{vetId}/delete")
	public String deleteVet(@PathVariable("vetId") int vetId) {
		vetService.deleteVetById(vetId);
		return "redirect:/vets";
	}

	@GetMapping(value = { "/vets.xml"})
	public @ResponseBody Vets showResourcesVetList() {
		// Here we are returning an object of type 'Vets' rather than a collection of Vet
		// objects
		// so it is simpler for JSon/Object mapping
		Vets vets = new Vets();
		vets.getVetList().addAll(this.vetService.findVets());
		return vets;
	}

    @ModelAttribute("specialties")
    public Collection<Specialty> vetSpecialties()
    {
       return this.vetService.findSpecialties();
    }

    @GetMapping(value = "/vets/{vetId}/edit")
    public String initUpdateVetForm( @PathVariable("vetId") int vetId, Map<String, Object> model )
    {
    	VetForm vetForm = new VetForm();
        Vet vet = this.vetService.findVetById( vetId );
        vetForm.setVet(vet);
        vetForm.setSpecialties(vet.getSpecialties().toArray(new Specialty[0]));
        model.put("vetForm",vetForm);
        return VIEW_UPDATE_ADD_VET;
    }

    @PostMapping(value = "/vets/{vetId}/edit")
    public String processUpdateOwnerForm( @Valid VetForm vetForm, BindingResult result,
                                         @PathVariable("vetId") int vetId )
    {
        if ( result.hasErrors() ) {
            return VIEW_UPDATE_ADD_VET;
        }
        else
        {
        	Vet vet = vetForm.getVet();
        	for(int i=0; i<vetForm.getSpecialties().length; i++) {
            	vet.addSpecialty(vetForm.getSpecialties()[i]);
        	}
            vet.setId( vetId );
            this.vetService.saveVet( vet );
            return "redirect:/vets";
        }
    }

    @GetMapping(value = "/vets/new")
    public String initCreationForm(Map<String, Object> model) {
        model.put("vetForm", new VetForm());
        return VIEW_UPDATE_ADD_VET;
    }

    @PostMapping(value = "/vets/new")
    public String processCreationForm( VetForm vetForm, BindingResult result, Map<String, Object> model) {
        if (result.hasErrors()) {
            return VIEW_UPDATE_ADD_VET;
        }
        else {
        	Vet vet = vetForm.getVet();
        	for(int i=0; i<vetForm.getSpecialties().length; i++) {
            	vet.addSpecialty(vetForm.getSpecialties()[i]);
        	}
        	this.vetService.saveVet( vet);
        }

            return "redirect:/vets";
        }
    }
