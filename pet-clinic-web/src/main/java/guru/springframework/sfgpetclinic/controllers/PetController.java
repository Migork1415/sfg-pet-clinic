package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Collection;

@Controller
@RequestMapping( "/owners/{ownerId}" )
@RequiredArgsConstructor
public class PetController {

	private static final String VIEWS_PETS_CREATE_OR_UPDATE_FORM = "pets/createOrUpdatePetForm";

	private final PetService petService;

	private final OwnerService ownerService;

	private final PetTypeService petTypeService;

	@InitBinder( "owner" )
	public void initOwnerBinder( WebDataBinder dataBinder ) {
		dataBinder.setDisallowedFields( "id" );
	}

	@ModelAttribute( "types" )
	public Collection<PetType> populatePetTypes() {
		return petTypeService.findAll();
	}

	@ModelAttribute( "owner" )
	public Owner findOwner( @PathVariable Long ownerId ) {
		return ownerService.findById( ownerId );
	}

	@GetMapping( "/pets/new" )
	public String initCreationForm( Owner owner, Model model ) {
		Pet pet = new Pet();
		owner.addPet( pet );
		model.addAttribute( "pet", pet );

		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping( "/pets/new" )
	public String processCreationForm( Owner owner, @Valid Pet pet, BindingResult result, ModelMap model ) {

		if ( StringUtils.hasLength( pet.getName() ) && pet.isNew() && owner.getPet( pet.getName(), true ) != null ) {
			result.rejectValue( "name", "duplicate", "already exists" );
		}

		owner.addPet( pet );

		if ( result.hasErrors() ) {
			model.put( "pet", pet );
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}
		else {
			petService.save( pet );

			return "redirect:/owners/" + owner.getId();
		}
	}

	@GetMapping( "/pets/{petId}/edit" )
	public String initUpdateForm( @PathVariable( "petId" ) Long petId, ModelMap model ) {
		Pet pet = petService.findById( petId );
		model.put( "pet", pet );

		return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
	}

	@PostMapping( "/pets/{petId}/edit" )
	public String processUpdateForm( @Valid Pet pet, BindingResult result, Owner owner, ModelMap model ) {

		if ( result.hasErrors() ) {
			pet.setOwner( owner );
			model.put( "pet", pet );
			return VIEWS_PETS_CREATE_OR_UPDATE_FORM;
		}
		else {
			owner.addPet( pet );
			petService.save( pet );
			return "redirect:/owners/" + owner.getId();
		}
	}

}
