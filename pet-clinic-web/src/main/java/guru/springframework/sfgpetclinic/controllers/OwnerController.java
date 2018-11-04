package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.services.OwnerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

	@RequestMapping({"/find"})
	public String findOwners(){
		return "notImplemented";
	}
}
