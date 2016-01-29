package uk.org.nottinghack.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import uk.org.nottinghack.domain.Transaction;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface TransactionRepository extends CrudRepository<Transaction, Integer>
{
    Page<Transaction> findByMemberIdOrderByDateTimeDesc(Integer memberId, Pageable pageable);
}
