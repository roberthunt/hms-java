package uk.org.nottinghack.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.repository.CrudRepository;
import uk.org.nottinghack.domain.MemberStatusUpdate;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface MemberStatusUpdateRepository extends CrudRepository<MemberStatusUpdate, Integer>
{
    // finds the latest status update for the members, eagerly loading the old status, new status and admin associations
    @EntityGraph(attributePaths = {"oldStatus", "newStatus", "admin"})
    MemberStatusUpdate findFirstByMemberIdOrderByTimestampDesc(Integer memberId);
}
