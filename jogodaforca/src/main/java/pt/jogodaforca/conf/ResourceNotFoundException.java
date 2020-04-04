package pt.jogodaforca.conf;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;;

/**
 * Classe trata erros
 * @author Walyson 
 * @version 1.0
 * @since   2019-12-05
 */
@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
	private static final long serialVersionUID = -6214707428057223228L;
}
