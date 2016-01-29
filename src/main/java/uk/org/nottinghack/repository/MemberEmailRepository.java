package uk.org.nottinghack.repository;

import org.springframework.data.repository.CrudRepository;
import uk.org.nottinghack.domain.MemberEmail;

import java.util.List;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface MemberEmailRepository extends CrudRepository<MemberEmail, Integer>
{
    // finds the latest email for the members
    MemberEmail findFirstByMemberIdOrderByTimestampDesc(Integer memberId);

    List<MemberEmail> findByMemberIdOrderByTimestampDesc(Integer memberId);
}
