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
		inverseJoinColumns = @JoinColumn(name = "specialty_id")
	)
	private Set<Specialty> specialties = new HashSet<>();

	@Builder
	public Vet( Long id, String firstName, String lastName, Set<Specialty> specialties ) {
		super( id, firstName, lastName );
		this.specialties = specialties;
	}

	public void addSpecialty( Specialty specialty ) {

		if ( specialties == null ) {
			specialties = new HashSet<>();
		}

		if ( specialty != null ) {
			specialties.add( specialty );
		}

	}
}
