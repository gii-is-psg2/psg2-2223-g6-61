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
import java.util.Collection;
import java.util.Map;

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

    @GetMapping(value = "/vet/{vetId}/edit")
    public String initUpdateVetForm( @PathVariable("vetId") int vetId, Model model )
    {
        Vet vet = this.vetService.findVetById( vetId );
        model.addAttribute( vet );
        return VIEW_UPDATE_ADD_VET;
    }

    @PostMapping(value = "/vet/{vetId}/edit")
    public String processUpdateOwnerForm( @Valid Vet vet, BindingResult result,
                                         @PathVariable("vetId") int vetId )
    {
        if ( result.hasErrors() ) {
            return VIEW_UPDATE_ADD_VET;
        }
        else
        {
            vet.setId( vetId );
            this.vetService.saveVet( vet );
            return "redirect:/vet/{vetId}";
        }
    }

    @GetMapping(value = "/vet/new")
    public String initCreationForm(Map<String, Object> model) {
        Vet newVet = new Vet();
        model.put("vet", newVet);
        return VIEW_UPDATE_ADD_VET;
    }

    @PostMapping(value = "/vet/new")
    public String processCreationForm(@Valid Vet newVet, BindingResult result) {
        if (result.hasErrors()) {
            return VIEW_UPDATE_ADD_VET;
        }
        else
            this.vetService.saveVet( newVet );

            return "redirect:/owners/" + newVet.getId();
        }
    }
