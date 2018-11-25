package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.VisitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping( "/owners/{ownerId}/pets/{petId}/visits" )
@RequiredArgsConstructor
public class VisitController {

	private final VisitService visitService;

	private final PetService petService;

	@InitBinder
	public void setAllowedFields( WebDataBinder dataBinder ) {
		dataBinder.setDisallowedFields( "id" );
	}

	/**
	 * Called before each and every @RequestMapping annotated method.
	 * 2 goals:
	 * - Make sure we always have fresh data
	 * - Since we do not use the session scope, make sure that Pet object always has an id
	 * (Even though id is not part of the form fields)
	 *
	 * @param petId
	 * @return Pet
	 */
	@ModelAttribute( "visit" )
	public Visit loadPetWithVisit( @PathVariable( "petId" ) Long petId, Model model ) {

		Pet pet = petService.findById( petId );
		model.addAttribute( "pet", pet );

		Visit visit = new Visit();
		pet.addVisit( visit );

		return visit;
	}

	// Spring MVC calls method loadPetWithVisit(...) before initNewVisitForm is called
	@GetMapping( "new" )
	public String initNewVisitForm() {
		return "pets/createOrUpdateVisitForm";
	}

	// Spring MVC calls method loadPetWithVisit(...) before processNewVisitForm is called
	@PostMapping( "new" )
	public String processNewVisitForm( @Valid Visit visit, BindingResult result, @PathVariable( "ownerId" ) Long ownerId ) {
		if ( result.hasErrors() ) {
			return "pets/createOrUpdateVisitForm";
		}
		else {
			visitService.save( visit );
			return "redirect:/owners/" + ownerId;
		}
	}
}
