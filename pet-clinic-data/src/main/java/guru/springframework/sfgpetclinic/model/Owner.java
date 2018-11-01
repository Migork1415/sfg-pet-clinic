package guru.springframework.sfgpetclinic.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Owner extends Person {

	private String address;

	private String city;

	private String telephone;

	@Setter( AccessLevel.PRIVATE )
	private Set<Pet> pets;

	public void addPet( Pet pet ) {

		if ( pets == null ) {
			pets = new HashSet<>();
		}

		if ( pet != null ) {
			pets.add( pet );
		}

	}
}
