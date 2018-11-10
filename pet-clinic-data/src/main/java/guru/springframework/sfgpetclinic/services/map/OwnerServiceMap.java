package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.model.Pet;
import guru.springframework.sfgpetclinic.services.OwnerService;
import guru.springframework.sfgpetclinic.services.PetService;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Service
@Profile( { "map", "default" } )
@RequiredArgsConstructor
public class OwnerServiceMap extends AbstractMapService<Owner, Long> implements OwnerService {

	private final PetTypeService petTypeService;

	private final PetService petService;

	@Override
	public Set<Owner> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById( Long id ) {
		super.deleteById( id );
	}

	@Override
	public void delete( Owner object ) {
		super.delete( object );
	}

	@Override
	public Owner save( Owner object ) {

		if ( object == null ) {
			return null;
		}

		if ( !CollectionUtils.isEmpty( object.getPets() ) ) {
			object.getPets().forEach( pet -> {
				if ( pet.getPetType() == null ) {
					throw new RuntimeException( "Pet type is required!" );
				}

				if ( pet.getPetType().getId() == null ) {
					pet.assignPetType( petTypeService.save( pet.getPetType() ) );
				}

				if( pet.getId() == null ) {
					Pet savedPet = petService.save( pet );
					pet.setId( savedPet.getId() );
				}
			} );
		}

		return super.save( object );
	}

	@Override
	public Owner findById( Long id ) {
		return super.findById( id );
	}

	@Override
	public Owner findByLastName( String lastName ) {
		//TODO: implement
		return null;
	}
}
