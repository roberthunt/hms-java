package uk.org.nottinghack.domain.bank;

import java.math.BigDecimal;
import java.time.LocalDate;


/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class TsbBankTransaction
{
    //private final DateTimeFormatter dtf = DateTimeFormat.forPattern("dd/MM/yyyy");

    private LocalDate date;
    private TsbBankTransactionType type;
    private String sortCode;
    private String accountNumber;
    private String description;
    private BigDecimal debitAmount;
    private BigDecimal creditAmount;
    private BigDecimal balanceAmount;

    public TsbBankTransaction(String date, String type, String sortCode, String accountNumber, String description,
                              String debitAmount, String creditAmount, String balanceAmount)
    {
        //this.date = dtf.parseLocalDate(date);
        this.type = TsbBankTransactionType.fromCode(type);
        this.sortCode = sortCode;
        this.accountNumber = accountNumber;
        this.description = description;
        this.debitAmount = debitAmount.isEmpty() ? null : new BigDecimal(debitAmount);
        this.creditAmount = creditAmount.isEmpty() ? null : new BigDecimal(creditAmount);
        this.balanceAmount = balanceAmount.isEmpty() ? null : new BigDecimal(balanceAmount);
    }

    @Override
    public String toString()
    {
        final StringBuilder sb = new StringBuilder("TsbBankTransaction{");
        sb.append("date=").append(date);
        sb.append(", type=").append(type);
        sb.append(", sortCode='").append(sortCode).append('\'');
        sb.append(", accountNumber='").append(accountNumber).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", debitAmount=").append(debitAmount);
        sb.append(", creditAmount=").append(creditAmount);
        sb.append(", balanceAmount=").append(balanceAmount);
        sb.append('}');
        return sb.toString();
    }
}
