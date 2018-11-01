package guru.springframework.sfgpetclinic.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode( callSuper = true )
public class Specialty extends BaseEntity {

	private String description;

}
