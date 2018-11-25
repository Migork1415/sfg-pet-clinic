package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping( "/owners" )
@RequiredArgsConstructor
public class OwnerController {

	private final OwnerService ownerService;

	@GetMapping
	public String listOnwers( Model model ) {

		model.addAttribute( "owners", ownerService.findAll() );

		return "owners/index";
	}

	@InitBinder
	public void setAllowedFields( WebDataBinder dataBinder ){
		dataBinder.setDisallowedFields( "id" );
	}

	@RequestMapping( { "/find" } )
	public String findOwners() {
		return "notImplemented";
	}

	/**
	 * Custom handler for displaying an owner.
	 *
	 * @param ownerId the ID of the owner to display
	 * @return a ModelMap with the model attributes for the view
	 */
	@GetMapping( "/{ownerId}" )
	public ModelAndView showOwner( @PathVariable( "ownerId" ) Long ownerId ) {
		ModelAndView mav = new ModelAndView( "owners/ownerDetails" );
		mav.addObject( ownerService.findById( ownerId ) );
		return mav;
	}
}
