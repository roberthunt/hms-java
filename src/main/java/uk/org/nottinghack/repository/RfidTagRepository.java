package uk.org.nottinghack.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import uk.org.nottinghack.domain.RfidTag;

import java.util.List;
import java.util.Optional;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface RfidTagRepository extends CrudRepository<RfidTag, String>
{
    @EntityGraph(attributePaths = {"member"})
    Optional<RfidTag> findBySerial(String serial);

    List<RfidTag> findByMemberId(Integer memberId);
}
