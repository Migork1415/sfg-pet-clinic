package guru.springframework.sfgpetclinic.services;

import guru.springframework.sfgpetclinic.model.Vat;
import org.springframework.data.repository.CrudRepository;

public interface VatService extends CrudRepository<Vat, Long> {

}
