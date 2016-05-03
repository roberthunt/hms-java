package uk.org.nottinghack.domain.bank;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 *
 * Based on the TSB website help pages (http://www.tsb.co.uk/help/support/), not all of these may appear in the
 * transaction type field.
 */
public enum TsbBankTransactionType
{
    BANK_GIRO_CREDIT("BGC"),
    BILL_PAYMENT("BP"),
    CARD("CD"),
    CHARGE("CHG"),
    CHEQUE("CHQ"),
    COMMISSION("COMM"),
    CORRECTION("COR"),
    CASHPOINT("CPT"),
    CASH("CSH"),
    CASH_CHEQUE("CSQ"),
    DIRECT_DEBIT("DD"),
    DEBIT_CARD("DEB"),
    DEPOSIT("DEP"),
    OVERDRAWN_BALANCE("DR"),
    EURO_CHEQUE("EUR"),
    FASTER_PAYMENTS_INCOMING("FPI"),
    FASTER_PAYMENTS_OUTGOING("FPO"),
    INTERNET_BANKING("IB"),
    MOBILE_TOP_UP("MTU"),
    PAYMENT("PAY"),
    PAYSAVE("PSV"),
    SALARY("SAL"),
    STANDING_ORDER("SO"),
    UNKNOWN("UNKNOWN");

    private final String code;

    private TsbBankTransactionType(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }

    public static TsbBankTransactionType fromCode(String code)
    {
        if (code != null)
        {
            for (TsbBankTransactionType tsbBankTransactionType : values())
            {
                if (code.equalsIgnoreCase(tsbBankTransactionType.code))
                {
                    return tsbBankTransactionType;
                }
            }
        }
        return UNKNOWN;
    }
}
