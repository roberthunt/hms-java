package uk.org.nottinghack.domain;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Entity
@Table(name = "transactions")
public class Transaction
{
    @Id
    @GeneratedValue
    @Column(name = "transaction_id")
    private int id;

    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Column(name = "transaction_datetime", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "amount")
    private int amount;

    @Column(name = "transaction_type", length = 6, nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @Column(name = "transaction_status", length = 8, nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus status;

    @Column(name = "transaction_desc", length = 512)
    private String description;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recorded_by")
    private Member recordedBy;

    public Transaction()
    {
        // default no-arg constructor
    }

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public Member getMember()
    {
        return member;
    }

    public void setMember(Member member)
    {
        this.member = member;
    }

    public LocalDateTime getDateTime()
    {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime)
    {
        this.dateTime = dateTime;
    }

    public int getAmount()
    {
        return amount;
    }

    public void setAmount(int amount)
    {
        this.amount = amount;
    }

    public TransactionType getType()
    {
        return type;
    }

    public void setType(TransactionType type)
    {
        this.type = type;
    }

    public TransactionStatus getStatus()
    {
        return status;
    }

    public void setStatus(TransactionStatus status)
    {
        this.status = status;
    }

    public String getDescription()
    {
        return description;
    }

    public void setDescription(String description)
    {
        this.description = description;
    }

    public Product getProduct()
    {
        return product;
    }

    public void setProduct(Product product)
    {
        this.product = product;
    }

    public Member getRecordedBy()
    {
        return recordedBy;
    }

    public void setRecordedBy(Member recordedBy)
    {
        this.recordedBy = recordedBy;
    }

    public double getAmountInPounds()
    {
        return getAmount() / 100;
    }
}
