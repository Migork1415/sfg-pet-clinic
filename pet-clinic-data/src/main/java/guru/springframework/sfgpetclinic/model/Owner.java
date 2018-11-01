package guru.springframework.sfgpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@EqualsAndHashCode( callSuper = true )
public class Owner extends Person {

	private String address;

	private String city;

	private String telephone;

	private Set<Pet> pets;

}
