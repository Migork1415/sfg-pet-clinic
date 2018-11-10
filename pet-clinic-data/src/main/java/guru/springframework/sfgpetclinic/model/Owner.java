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
@Table( name = "owners" )
public class Owner extends Person {

	@Column( name = "address" )
	private String address;

	@Column( name = "city" )
	private String city;

	@Column( name = "telephone" )
	private String telephone;

	@OneToMany( cascade = CascadeType.ALL, mappedBy = "owner" )
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
