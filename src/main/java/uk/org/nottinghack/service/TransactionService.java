package uk.org.nottinghack.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import uk.org.nottinghack.domain.Transaction;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public interface TransactionService
{
    Page<Transaction> getTransactionsByMemberId(Integer memberId, Pageable pageable);
}
