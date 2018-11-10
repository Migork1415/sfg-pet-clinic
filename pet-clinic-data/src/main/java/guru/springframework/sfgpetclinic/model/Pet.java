package guru.springframework.sfgpetclinic.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table( name = "pets" )
public class Pet extends BaseEntity {

	@Column( name = "name" )
	private String name;

	@ManyToOne
	@JoinColumn( name = "type_id")
	private PetType petType;

	@ManyToOne
	@JoinColumn( name = "owner_id" )
	private Owner owner;

	@Column(name = "birth_day")
	private LocalDate birthDay;

	public void assignPetType( PetType petType ) {
		this.petType = petType;
	}
}
