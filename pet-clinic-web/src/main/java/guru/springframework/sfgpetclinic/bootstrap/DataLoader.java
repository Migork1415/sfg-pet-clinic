package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.VetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;

	private final VetService vetService;

	private final PetTypeService petTypeService;

	@Override
	public void run( String... args ) throws Exception {
		///////////////////
		// PET TYPES
		///////////////////
		PetType dog = new PetType();
		dog.setName( "Dog" );
		PetType savedDogPetType = petTypeService.save( dog );

		PetType cat = new PetType();
		cat.setName( "Cat" );
		PetType savedCatPetType = petTypeService.save( cat );

		log.info( "Loaded Pet types..." );

		///////////////////
		// OWNERS && PETS
		///////////////////
		Owner owner1 = new Owner();
		owner1.setFirstName( "Michael" );
		owner1.setLastName( "Weston" );
		owner1.setAddress( "123 Brick street" );
		owner1.setCity( "Miami" );
		owner1.setTelephone( "917081212" );

		Pet mikesPet = new Pet();
		mikesPet.setPetType( savedDogPetType );
		mikesPet.setOwner( owner1 );
		mikesPet.setBirthDay( LocalDate.of( 2010, 01, 05 ) );
		mikesPet.setName( "Rosco" );
		owner1.addPet( mikesPet );

		Owner owner2 = new Owner();
		owner2.setFirstName( "Fiona" );
		owner2.setLastName( "Glemanne" );
		owner2.setAddress( "123 Brick street" );
		owner2.setCity( "Miami" );
		owner2.setTelephone( "917081212" );

		Pet fionaPet = new Pet();
		fionaPet.setPetType( savedCatPetType );
		fionaPet.setOwner( owner1 );
		fionaPet.setBirthDay( LocalDate.of( 2011, 02, 12 ) );
		fionaPet.setName( "Mia" );
		owner2.addPet( fionaPet );

		ownerService.save( owner1 );
		ownerService.save( owner2 );

		log.info( "Loaded Owners..." );

		///////////////////
		// VETS
		///////////////////
		Vet vet1 = new Vet();
		vet1.setFirstName( "Sam" );
		vet1.setLastName( "Axe" );

		Vet vet2 = new Vet();
		vet2.setFirstName( "Jessie" );
		vet2.setLastName( "Porter" );

		vetService.save( vet1 );
		vetService.save( vet2 );

		log.info( "Loaded Vets..." );
	}
}
