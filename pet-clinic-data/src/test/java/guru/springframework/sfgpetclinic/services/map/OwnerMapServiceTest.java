package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class OwnerMapServiceTest {

	private OwnerMapService ownerMapService;

	private final Long ownerId = 1L;

	private final String lastName = "Miranda";

	@BeforeEach
	void setUp() {
		ownerMapService = new OwnerMapService( new PetTypeMapService(), new PetMapService() );

		ownerMapService.save( Owner.builder()
			.id( ownerId )
			.lastName( lastName )
			.build() );
	}

	@Test
	void findAll() {
		Set<Owner> owners = ownerMapService.findAll();

		assertEquals( 1, owners.size() );
	}

	@Test
	void deleteById() {
		Owner owner = ownerMapService.findById( ownerId );

		assertEquals( ownerId, owner.getId() );
	}

	@Test
	void delete() {
		ownerMapService.delete( ownerMapService.findById( ownerId ) );
		assertEquals( 0, ownerMapService.findAll().size() );
	}

	@Test
	void save() {
		final Long id = 2L;
		Owner owner2 =	ownerMapService.save( Owner.builder()
			.id( id )
			.build() );

		Set<Owner> owners = ownerMapService.findAll();

		assertEquals( id, owner2.getId() );
		assertEquals( 2, owners.size() );
	}

	@Test
	void findById() {
		ownerMapService.deleteById( ownerId );

		assertNull( ownerMapService.findById( ownerId ) );
	}

	@Test
	void findByLastName() {

		assertNotNull( ownerMapService.findByLastName( lastName ) );
		assertEquals( ownerId, ownerMapService.findByLastName( lastName ).getId() );


	}
}