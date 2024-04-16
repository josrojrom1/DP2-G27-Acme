
package acme.features.any.claim;

import java.util.Collection;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import acme.client.repositories.AbstractRepository;
import acme.entities.claims.Claim;

@Repository
public interface AnyClaimsRepository extends AbstractRepository {

	@Query("select a from Claim a where a.id = :id")
	Claim findOneClaimById(int id);

	@Query("select a from Claim a")
	Collection<Claim> findAllClaim();
}
