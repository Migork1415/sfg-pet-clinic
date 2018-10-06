package guru.springframework.sfgpetclinic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/owners")
@RequiredArgsConstructor
public class OwnerController {

	@GetMapping
	public String listOnwers(){
		return "owners/index";
	}

}
