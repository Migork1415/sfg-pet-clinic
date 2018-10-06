package guru.springframework.sfgpetclinic.services.map;

import guru.springframework.sfgpetclinic.model.Vat;
import guru.springframework.sfgpetclinic.services.CrudService;
import org.springframework.context.annotation.Profile;

import java.util.Set;

@Profile( "map" )
public class VatServiceMap extends AbstractMapService<Vat, Long> implements CrudService<Vat, Long> {

	@Override
	public Set<Vat> findAll() {
		return super.findAll();
	}

	@Override
	public void deleteById( Long id ) {
		super.deleteById( id );
	}

	@Override
	public void delete( Vat object ) {
		super.delete( object );
	}

	@Override
	public Vat save( Vat object ) {
		return super.save( object.getId(), object );
	}

	@Override
	public Vat findById( Long id ) {
		return super.findById( id );
	}
}
