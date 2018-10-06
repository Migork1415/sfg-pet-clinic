package guru.springframework.sfgpetclinic.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
@RequiredArgsConstructor
public class IndexController {

	@GetMapping( { "", "/", "index","index.html"} )
	public String index() {
		return "index";
	}

}
