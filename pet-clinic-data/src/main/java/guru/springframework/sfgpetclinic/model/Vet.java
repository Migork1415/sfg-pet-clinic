package guru.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "vets" )
public class Vet extends Person {

	@Setter( AccessLevel.PRIVATE )
	@ManyToMany( fetch = FetchType.EAGER )
	@JoinTable( name = "vet_specialties",
		joinColumns = @JoinColumn(name = "vet_id"),
		inverseJoinColumns = @JoinColumn(name = "speciality_id")
	)
	private Set<Speciality> specialties = new HashSet<>();

	public void addSpecialty( Speciality specialty ) {

		if ( specialties == null ) {
			specialties = new HashSet<>();
		}

		if ( specialty != null ) {
			specialties.add( specialty );
		}

	}
}
