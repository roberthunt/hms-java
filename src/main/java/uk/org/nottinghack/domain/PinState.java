package uk.org.nottinghack.domain;

/**
 * Created by Rob on 09/01/2015.
 */
public enum PinState
{
    /**
     * This pin can be used for entry (up until the expiry date), cannot be used to register a card.
     */
    STATE_ACTIVE(10),

    /**
    * Pin has expired and can no longer be used for entry.
    */
    STATE_EXPIRED(20),

    /**
    * This pin cannot be used for entry, and has likely been used to activate an RFID card.
    */
    STATE_CANCELLED(30),

    /**
     * This pin may be used to enrol an RFID card.
     */
    STATE_ENROLL(40);

    private final int value;

    PinState(int value)
    {
        this.value = value;
    }


}
