package uk.org.nottinghack.domain;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public enum MemberStatus
{
    PROSPECTIVE_MEMBER(1, "Prospective Member")
    {
        @Override
        public boolean canProceedTo(MemberStatus newStatus)
        {
            if (newStatus.equals(WAITING_FOR_CONTACT_DETAILS))
            {
                return true;
            }
            return false;
        }
    },

    // PRE_MEMBER_1
    WAITING_FOR_CONTACT_DETAILS(2, "Waiting for contact details")
    {
        @Override
        public boolean canProceedTo(MemberStatus newStatus)
        {
            if (newStatus.equals(WAITING_FOR_MEMBERSHIP_ADMIN_TO_APPROVE_CONTACT_DETAILS))
            {
                return true;
            }
            return false;
        }
    },

    // PRE_MEMBER_2
    WAITING_FOR_MEMBERSHIP_ADMIN_TO_APPROVE_CONTACT_DETAILS(3, "Waiting for Membership Admin to approve contact details")
    {
        @Override
        public boolean canProceedTo(MemberStatus newStatus)
        {
            if (newStatus.equals(WAITING_FOR_STANDING_ORDER_PAYMENT))
            {
                return true;
            }
            return false;
        }
    },

    // PRE_MEMBER_3
    WAITING_FOR_STANDING_ORDER_PAYMENT(4, "Waiting for standing order payment")
    {
        @Override
        public boolean canProceedTo(MemberStatus newStatus)
        {
            if (newStatus.equals(CURRENT_MEMBER))
            {
                return true;
            }
            return false;
        }
    },

    CURRENT_MEMBER(5, "Current Member")
    {
        @Override
        public boolean canProceedTo(MemberStatus newStatus)
        {
            if (newStatus.equals(EX_MEMBER))
            {
                return true;
            }
            return false;
        }
    },

    EX_MEMBER(6, "Ex Member")
    {
        @Override
        public boolean canProceedTo(MemberStatus newStatus)
        {
            if (newStatus.equals(CURRENT_MEMBER))
            {
                return true;
            }
            return false;
        }
    };

    private final int id;
    private final String title;

    MemberStatus(int id, String title)
    {
        this.id = id;
        this.title = title;
    }

    public int getId()
    {
        return id;
    }

    public String getTitle()
    {
        return title;
    }

    public abstract boolean canProceedTo(MemberStatus newStatus);
}
