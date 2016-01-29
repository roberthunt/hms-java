package uk.org.nottinghack.domain.bank;

/**
 * Created by Rob on 16/02/2015.
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
