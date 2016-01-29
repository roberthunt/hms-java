package uk.org.nottinghack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import uk.org.nottinghack.domain.Member;
import uk.org.nottinghack.service.MemberService;
import uk.org.nottinghack.service.TransactionService;

/**
 * This replaces the "snackspace" controller in HMS-PHP
 *
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Controller
@RequestMapping("/transactions")
public class TransactionController
{
    private static final String TRANSACTION_LIST_VIEW = "transaction/list";

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private MemberService memberService;

    @RequestMapping
    public String index(@AuthenticationPrincipal Member currentMember, Pageable pageable, Model model)
    {
        return list(currentMember.getId(), pageable, model);
    }

    @RequestMapping({"/list/{memberId}"})
    public String list(@PathVariable int memberId, Pageable pageable, Model model)
    {
        model.addAttribute("memberId", memberId);
        model.addAttribute("transactions", transactionService.getTransactionsByMemberId(memberId, pageable));
        model.addAttribute("balance", memberService.getMember(memberId).getBalanceInPounds());
        return TRANSACTION_LIST_VIEW;
    }
}
