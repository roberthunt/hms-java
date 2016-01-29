package uk.org.nottinghack.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * This class defines all of the available permissions which can be granted to a group. Additional permissions may
 * be added to the database but this class will need to be kept in sync to make the additional permissions available to
 * the application.
 *
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
@Entity
@Table(name = "permissions")
public class Permission
{
    @Id
    @Column(name = "permission_code", length = 16, insertable = false, updatable = false)
    private String code;

    @Column(name = "permission_desc", length = 200, insertable = false, updatable = false)
    private String description;

    public Permission()
    {
        // default no-arg constructor
    }

    public String getCode()
    {
        return code;
    }

    public String getDescription()
    {
        return description;
    }

    /*
              SELECT
                CONCAT('public static final String ', TRIM(permission_code), ' = "', TRIM(permission_code), '";')
              FROM
                permissions;
            */
    public static final String ADD_GROUP = "ADD_GROUP";
    public static final String ADD_GRP_MEMBER = "ADD_GRP_MEMBER";
    public static final String ADD_UPD_PRODUCT = "ADD_UPD_PRODUCT";
    public static final String AMEND_PINS = "AMEND_PINS";
    public static final String CHG_GRP_PERM = "CHG_GRP_PERM";
    public static final String DEL_GROUP = "DEL_GROUP";
    public static final String REC_TRAN = "REC_TRAN";
    public static final String REC_TRAN_OWN = "REC_TRAN_OWN";
    public static final String REM_GRP_MEMBER = "REM_GRP_MEMBER";
    public static final String SET_CREDIT_LIMIT = "SET_CREDIT_LIMIT";
    public static final String SET_PASSWORD = "SET_PASSWORD";
    public static final String UPD_VEND_CONFIG = "UPD_VEND_CONFIG";
    public static final String VIEW_ACCESS_MEM = "VIEW_ACCESS_MEM";
    public static final String VIEW_BALANCES = "VIEW_BALANCES";
    public static final String VIEW_GROUPS = "VIEW_GROUPS";
    public static final String VIEW_GRP_MEMBERS = "VIEW_GRP_MEMBERS";
    public static final String VIEW_GRP_PERMIS = "VIEW_GRP_PERMIS";
    public static final String VIEW_MEMBERS = "VIEW_MEMBERS";
    public static final String VIEW_MEMBER_LIST = "VIEW_MEMBER_LIST";
    public static final String VIEW_MEMBER_PINS = "VIEW_MEMBER_PINS";
    public static final String VIEW_MEMBER_RFID = "VIEW_MEMBER_RFID";
    public static final String VIEW_OWN_TRANS = "VIEW_OWN_TRANS";
    public static final String VIEW_PRD_DETAIL = "VIEW_PRD_DETAIL";
    public static final String VIEW_PRODUCTS = "VIEW_PRODUCTS";
    public static final String VIEW_SALES = "VIEW_SALES";
    public static final String VIEW_TRANS = "VIEW_TRANS";
    public static final String VIEW_VEND_CONFIG = "VIEW_VEND_CONFIG";
    public static final String VIEW_VEND_LOG = "VIEW_VEND_LOG";
    public static final String WEB_LOGON = "WEB_LOGON";
    public static final String WIFI_ACCESS = "WIFI_ACCESS";
    public static final String WIKI_ACCESS = "WIKI_ACCESS";

    /* HMS Java additions */

    // Controls visibility of other members details
    public static final String VIEW_OTHER_MEMBERS = "VIEW_OTHER_MEMBERS";
    public static final String EDIT_OTHER_MEMBERS = "EDIT_OTHER_MEMBERS";

    // Controls visibility of pins, status updates and groups when viewing a member
    public static final String VIEW_MEMBER_ADMIN_FEATURES = "VIEW_MEMBER_ADMIN_FEATURES";

    // Controls visibility of balance and credit limit when viewing a member
    public static final String VIEW_MEMBER_FINANCES = "VIEW_MEMBER_FINANCES";

    // Controls visibility of address and contact number when viewing a member
    public static final String VIEW_MEMBER_PERSONAL_DETAILS = "VIEW_MEMBER_PERSONAL_DETAILS";

    public static final String VIEW_TRANSACTIONS_OWN = "VIEW_TRANSACTIONS_OWN";
    public static final String VIEW_TRANSACTIONS_OTHER = "VIEW_TRANSACTIONS_OTHER";

    // allows the user to view the list of emails sent by HMS to a member (perm/view other)
    // full access, membership admin, membership
    public static final String VIEW_EMAILS = "VIEW_EMAILS";

    // allows the user to view the list of rfid cards that they have registered (perm/view other)
    // full access, membership admin, membership
    public static final String EDIT_OWN_RFID_CARDS = "EDIT_OWN_RFID_CARDS";
    public static final String VIEW_OTHER_RFID_CARDS = "VIEW_OTHER_RFID_CARDS";
    public static final String EDIT_OTHER_RFID_CARDS = "EDIT_OTHER_RFID_CARDS";


    // Tools

    // can the user see the tools nav link and list the tools?
    // this might be useful for "non-members" who hold a HMS account for other purposes (cleaners etc?)
    public static final String VIEW_TOOLS = "VIEW_TOOLS";

    public static final String ADD_TOOL = "ADD_TOOL";
    public static final String EDIT_TOOL = "EDIT_TOOL";

    //TODO: would be useful to have a structured naming conversion for VIEW, EDIT, VIEW OTHER, EDIT OTHER
}


