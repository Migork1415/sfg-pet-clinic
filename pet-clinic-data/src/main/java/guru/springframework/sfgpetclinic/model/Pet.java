package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Pet extends BaseEntity {

	private String name;

	private PetType petType;

	private Owner owner;

	private LocalDate birthDay;

	public void assignPetType( PetType petType ){
		this.petType = petType;
	}
}
