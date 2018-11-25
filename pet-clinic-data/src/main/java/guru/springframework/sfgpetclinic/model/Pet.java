package guru.springframework.sfgpetclinic.model;

import lombok.*;
import org.springframework.beans.support.MutableSortDefinition;
import org.springframework.beans.support.PropertyComparator;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.*;

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
	@JoinColumn( name = "type_id" )
	private PetType petType;

	@ManyToOne
	@JoinColumn( name = "owner_id" )
	private Owner owner;

	@Column( name = "birth_day" )
	private LocalDate birthDate;

	@OneToMany( cascade = CascadeType.ALL, mappedBy = "pet" )
	private Set<Visit> visits = new HashSet<>();

	@Builder
	public Pet( Long id, String name, PetType petType, Owner owner, LocalDate birthDate, Set<Visit> visits ) {
		super( id );
		this.name = name;
		this.petType = petType;
		this.owner = owner;
		this.birthDate = birthDate;
		this.visits = visits;
	}

	public void assignPetType( PetType petType ) {
		this.petType = petType;
	}

	protected Set<Visit> getVisitsInternal() {
		if ( this.visits == null ) {
			this.visits = new HashSet<>();
		}
		return this.visits;
	}

	protected void setVisitsInternal( Set<Visit> visits ) {
		this.visits = visits;
	}

	public List<Visit> getVisits() {
		List<Visit> sortedVisits = new ArrayList<>( getVisitsInternal() );
		PropertyComparator.sort( sortedVisits,
			new MutableSortDefinition( "date", false, false ) );
		return Collections.unmodifiableList( sortedVisits );
	}

	public void addVisit( Visit visit ) {
		getVisitsInternal().add( visit );
		visit.setPet( this );
	}
}
