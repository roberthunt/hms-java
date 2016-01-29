package uk.org.nottinghack.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.querydsl.QueryDslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.domain.MemberStatus;
import uk.org.nottinghack.repository.custom.MemberRepositoryCustom;

import java.util.Optional;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface MemberRepository extends CrudRepository<Member, Integer>, QueryDslPredicateExecutor<Member>,
        MemberRepositoryCustom
{
    // WARNING: multiple collection fetch, possible performance problem: cartesian product (result rows = pins * groups)
    @EntityGraph(attributePaths = {"account", "pins", "groups"})
    Optional<Member> findById(Integer memberId);

    @EntityGraph(attributePaths = {"groups.permissions"})
    Optional<Member> findByUsername(String username);

    @EntityGraph(attributePaths = {"account", "pins"})
    Optional<Member> findByEmail(String email);

    @EntityGraph(attributePaths = {"account"})
    Page<Member> findAll(Pageable pageable);

    @EntityGraph(attributePaths = {"account", "groups.permissions"})
    Page<Member> findByStatus(MemberStatus status, Pageable pageable);
}
