package guru.springframework.sfgpetclinic.bootstrap;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.VetService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

	private final OwnerService ownerService;

	private final VetService vetService;

	@Override
	public void run( String... args ) throws Exception {
		Owner owner1 = new Owner();
		owner1.setFirstName( "Michael" );
		owner1.setLastName( "Weston" );

		Owner owner2 = new Owner();
		owner2.setFirstName( "Fiona" );
		owner2.setLastName( "Glemanne" );

		ownerService.save( owner1 );
		ownerService.save( owner2 );

		log.info( "Loaded Owners..." );

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
