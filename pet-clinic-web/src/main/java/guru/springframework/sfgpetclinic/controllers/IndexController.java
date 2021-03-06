package guru.springframework.sfgpetclinic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping( "/" )
@RequiredArgsConstructor
public class IndexController {

	@GetMapping
	public String index() {
		return "index";
	}

	@RequestMapping({"/oups"})
	public String findOwners(){
		return "notImplemented";
	}

}
