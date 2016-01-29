package uk.org.nottinghack.builder;

import uk.org.nottinghack.domain.Account;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class AccountBuilder
{
    int id;
    String paymentReference;

    public AccountBuilder id(int id)
    {
        this.id = id;
        return this;
    }

    public AccountBuilder paymentReference(String paymentReference)
    {
        this.paymentReference = paymentReference;
        return this;
    }

    public Account build()
    {
        return new Account(id, paymentReference);
    }
}
