package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Vat;

import java.util.Set;

public interface VatService {

	Vat findById( Long id );

	Vat save( Vat vat );

	Set<Vat> findAll();

}
