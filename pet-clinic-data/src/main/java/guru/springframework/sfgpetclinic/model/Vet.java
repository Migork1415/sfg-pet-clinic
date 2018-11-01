package guru.springframework.sfgpetclinic.model;

import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Vet extends Person {

	@Setter( AccessLevel.PRIVATE )
	private Set<Specialty> specialties;

	public void addSpecialty( Specialty specialty ) {

		if ( specialties == null ) {
			specialties = new HashSet<>();
		}

		if ( specialty != null ) {
			specialties.add( specialty );
		}

	}
}
