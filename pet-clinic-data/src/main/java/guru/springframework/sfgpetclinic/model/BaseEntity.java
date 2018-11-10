package guru.springframework.sfgpetclinic.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;

@Data
@NoArgsConstructor
@MappedSuperclass
public class BaseEntity implements Serializable {

	private static final long serialVersionUID = 2441302184792880252L;

	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY )
	private Long id;

}
