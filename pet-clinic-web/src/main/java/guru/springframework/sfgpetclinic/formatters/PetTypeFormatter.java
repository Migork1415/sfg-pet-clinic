package guru.springframework.sfgpetclinic.formatters;

import guru.springframework.sfgpetclinic.model.PetType;
import guru.springframework.sfgpetclinic.services.PetTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.util.Collection;
import java.util.Locale;

/**
 * Instructs Spring MVC on how to parse and print elements of type 'PetType'. Starting from Spring 3.0, Formatters have
 * come as an improvement in comparison to legacy PropertyEditors. See the following links for more details: - The
 * Spring ref doc: http://static.springsource.org/spring/docs/current/spring-framework-reference/html/validation.html#format-Formatter-SPI
 * - A nice blog entry from Gordon Dickens: http://gordondickens.com/wordpress/2010/09/30/using-spring-3-0-custom-type-converter/
 * <p/>
 *
 * @author Mark Fisher
 * @author Juergen Hoeller
 * @author Michael Isvy
 */
@Component
@RequiredArgsConstructor
public class PetTypeFormatter implements Formatter<PetType> {

	private final PetTypeService petTypeService;

	@Override
	public String print( PetType petType, Locale locale ) {
		return petType.getName();
	}

	@Override
	public PetType parse( String text, Locale locale ) throws ParseException {
		Collection<PetType> findPetTypes = petTypeService.findAll();
		for ( PetType type : findPetTypes ) {
			if ( type.getName().equals( text ) ) {
				return type;
			}
		}
		throw new ParseException( "type not found: " + text, 0 );
	}

}
