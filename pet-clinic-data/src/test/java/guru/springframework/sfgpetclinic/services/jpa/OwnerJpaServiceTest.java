package guru.springframework.sfgpetclinic.services.jpa;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.repositories.OwnerRepository;
import guru.springframework.sfgpetclinic.repositories.PetRepository;
import guru.springframework.sfgpetclinic.repositories.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith( MockitoExtension.class )
class OwnerJpaServiceTest {

	private static final Long ownerId = 1L;

	private static final String lastName = "Miranda";

	@Mock
	private OwnerRepository ownerRepository;

	@Mock
	private PetRepository petRepository;

	@Mock
	private PetTypeRepository petTypeRepository;

	@InjectMocks
	private OwnerJpaService service;

	@BeforeEach
	void setUp() {

	}

	@Test
	void findByLastName() {
		Owner miranda = Owner.builder().id( ownerId ).lastName( lastName ).build();

		when( ownerRepository.findByLastName( any() ) ).thenReturn( Optional.of( miranda ) );

		assertEquals( ownerId, service.findByLastName( lastName ).getId() );
		assertEquals( lastName, service.findByLastName( lastName ).getLastName() );
	}

	@Test
	void findAll() {
		Owner miranda = Owner.builder().id( ownerId ).lastName( lastName ).build();
		Owner mirandam = Owner.builder().id( ownerId + 1 ).lastName( lastName + "m" ).build();

		Set<Owner> returnOwners = new HashSet<>();
		returnOwners.add( miranda );
		returnOwners.add( mirandam );

		when( ownerRepository.findAll() ).thenReturn( returnOwners );

		Set<Owner> owners = service.findAll();

		assertNotNull( owners );
		assertEquals( 2, owners.size() );
	}

	@Test
	void findById() {
		Owner miranda = Owner.builder().id( ownerId ).lastName( lastName ).build();

		when( ownerRepository.findById( any()) ).thenReturn( Optional.of( miranda ) );

		assertNotNull( service.findById( ownerId ) );
	}

	@Test
	void save() {
		Owner miranda = Owner.builder().id( ownerId ).lastName( lastName ).build();
		when( ownerRepository.save( any() ) ).thenReturn( miranda );

		assertNotNull( service.save( miranda ) );

	}

	@Test
	void delete() {
		Owner miranda = Owner.builder().id( ownerId ).lastName( lastName ).build();

		service.delete( miranda );
		verify( ownerRepository ).delete( any() );
	}

	@Test
	void deleteById() {
		service.deleteById( ownerId );
		verify( ownerRepository ).deleteById( anyLong() );
	}
}