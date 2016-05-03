package uk.org.nottinghack.domain.bank;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public enum NatwestBankTransactionType
{
    BACS("BAC"),
    DIRECT_DEBIT("D/D"),
    BANK_GIRO_CREDIT("BGC"),
    UNKNOWN("");

    private final String code;

    NatwestBankTransactionType(String code)
    {
        this.code = code;
    }

    public String getCode()
    {
        return code;
    }
}
