package uk.org.nottinghack.domain;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public enum TransactionType
{
    VEND, // transaction relates to either vending machine purchase, or a payment received by note acceptor
    MANUAL, // transaction is a manually entered (via web interface) record of a payment or purchase
    TOOL
}
