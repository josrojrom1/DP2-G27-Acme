
package acme.entities.currencies;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.client.data.AbstractEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SystemCurrency extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	private String				systemCurrency;

	@NotBlank
	private String				acceptedCurrencies;

}
