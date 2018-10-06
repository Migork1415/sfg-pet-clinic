package guru.springframework.sfgpetclinic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller("/vets")
@RequiredArgsConstructor
public class VetController {

	@GetMapping
	public String listVets(){
		return "vets/index";
	}

}
