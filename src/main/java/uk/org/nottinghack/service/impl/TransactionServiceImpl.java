package uk.org.nottinghack.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import uk.org.nottinghack.domain.Permission;
import uk.org.nottinghack.domain.Transaction;
import uk.org.nottinghack.repository.TransactionRepository;
import uk.org.nottinghack.service.TransactionService;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Service
public class TransactionServiceImpl implements TransactionService
{
    @Autowired
    private TransactionRepository transactionRepository;

    @Override
    @PreAuthorize("#memberId == principal.id or hasAuthority('" + Permission.VIEW_TRANSACTIONS_OTHER + "')")
    public Page<Transaction> getTransactionsByMemberId(Integer memberId, Pageable pageable)
    {
        return transactionRepository.findByMemberIdOrderByDateTimeDesc(memberId, pageable);
    }
}
