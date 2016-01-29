package uk.org.nottinghack.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Rob on 08/01/2015.
 */
@Entity
@Table(name = "account")
public class Account implements Serializable
{
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    private int id;

    @Column(name = "payment_ref", length = 18, nullable = false)
    private String paymentReference;

    public Account()
    {
        // default no-arg constructor
    }

    public Account(int id, String paymentReference)
    {
        this.id = id;
        this.paymentReference = paymentReference;
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getPaymentReference()
    {
        return paymentReference;
    }

    public void setPaymentReference(String paymentReference)
    {
        this.paymentReference = paymentReference;
    }
}
