package uk.org.nottinghack.domain;

import javax.persistence.*;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Entity
@Table(name = "products")
public class Product
{
    @Id
    @GeneratedValue
    @Column(name = "product_id")
    private int id;

    @Column(name = "price", nullable = false)
    private int price;

    @Column(name = "barcode", length = 25)
    private String barcode;

    @Column(name = "available")
    private int available;

    @Column(name = "shortdesc", length = 25)
    private String shortDescription;

    @Column(name = "longdesc", length = 512)
    private String lopngDescription;

    public Product()
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

    public int getPrice()
    {
        return price;
    }

    public void setPrice(int price)
    {
        this.price = price;
    }

    public String getBarcode()
    {
        return barcode;
    }

    public void setBarcode(String barcode)
    {
        this.barcode = barcode;
    }

    public int getAvailable()
    {
        return available;
    }

    public void setAvailable(int available)
    {
        this.available = available;
    }

    public String getShortDescription()
    {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription)
    {
        this.shortDescription = shortDescription;
    }

    public String getLopngDescription()
    {
        return lopngDescription;
    }

    public void setLopngDescription(String lopngDescription)
    {
        this.lopngDescription = lopngDescription;
    }
}
