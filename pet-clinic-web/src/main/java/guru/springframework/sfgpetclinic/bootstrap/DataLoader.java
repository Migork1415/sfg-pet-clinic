package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.*;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
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

	private final SpecialtyService specialtyService;

	@Override
	public void run( String... args ) {

		int count = petTypeService.findAll().size();

		if ( count == 0 ) {
			loadData();
		}
	}

	private void loadData() {
		///////////////////
		// PET TYPES
		///////////////////
		PetType dog = petTypeService.save( new PetType( "Dog" ) );
		PetType cat = petTypeService.save( new PetType( "Cat" ) );

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
		mikesPet.setPetType( dog );
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
		fionaPet.setPetType( cat );
		fionaPet.setOwner( owner1 );
		fionaPet.setBirthDay( LocalDate.of( 2011, 02, 12 ) );
		fionaPet.setName( "Mia" );
		owner2.addPet( fionaPet );

		ownerService.save( owner1 );
		ownerService.save( owner2 );

		log.info( "Loaded Owners..." );

		///////////////////
		// SPECIALTIES
		///////////////////
		Speciality radiology = specialtyService.save( new Speciality( "radiology" ) );
		Speciality surgery = specialtyService.save( new Speciality( "surgery" ) );
		Speciality dentistry = specialtyService.save( new Speciality( "dentistry" ) );

		log.info( "Loaded Specialties..." );

		///////////////////
		// VETS
		///////////////////
		Vet vet1 = new Vet();
		vet1.setFirstName( "Sam" );
		vet1.setLastName( "Axe" );
		vet1.addSpecialty( surgery );

		Vet vet2 = new Vet();
		vet2.setFirstName( "Jessie" );
		vet2.setLastName( "Porter" );
		vet2.addSpecialty( radiology );
		vet2.addSpecialty( dentistry );

		vetService.save( vet1 );
		vetService.save( vet2 );

		log.info( "Loaded Vets..." );
	}
}
