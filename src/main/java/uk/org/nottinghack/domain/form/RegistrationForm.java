package uk.org.nottinghack.domain.form;

import java.util.List;

/**
 * * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class RegistrationForm
{
    private String email;
    private List<Integer> mailingListIds;

    public RegistrationForm()
    {
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail(String email)
    {
        this.email = email;
    }

    public List<Integer> getMailingListIds()
    {
        return mailingListIds;
    }

    public void setMailingListIds(List<Integer> mailingListIds)
    {
        this.mailingListIds = mailingListIds;
    }
}
