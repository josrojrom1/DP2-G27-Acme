
package acme.entities.contracts;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

import acme.client.data.AbstractEntity;
import acme.client.data.datatypes.Money;
import acme.roles.Client;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Contract extends AbstractEntity {

	private static final long	serialVersionUID	= 1L;

	@NotBlank
	@Pattern(regexp = "[A-Z]{1,3}-\\d{3}")
	@Column(unique = true)
	private String				code;

	@Temporal(TemporalType.TIMESTAMP)
	@Past
	@NotNull
	private Date				instantiationMoment;

	@NotBlank
	@Length(max = 76)
	private String				provider;

	@NotBlank
	@Length(max = 76)
	private String				customer;

	@NotBlank
	@Length(max = 101)
	private String				goals;

	// less than or equal to the corresponding project cost
	@Valid
	private Money				budget;

	/*
	 * @NotNull
	 * 
	 * @Valid
	 * 
	 * @ManyToOne
	 * private Project project;
	 */

	@NotNull
	@Valid
	@ManyToOne(optional = false)
	private Client				client;

	@NotNull
	@Valid
	@OneToMany
	private List<ProgressLog>	progressLogs;

}