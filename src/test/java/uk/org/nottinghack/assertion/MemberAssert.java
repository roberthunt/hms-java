package uk.org.nottinghack.assertion;

/**
 * AssertJ custom assertion for members.
 *
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
import org.assertj.core.api.AbstractAssert;
import org.assertj.core.api.Assertions;
import uk.org.nottinghack.domain.Member;

public class MemberAssert extends AbstractAssert<MemberAssert, Member>
{
    private MemberAssert(Member actual)
    {
        super(actual, MemberAssert.class);
    }

    public static MemberAssert assertThatMember(Member actual)
    {
        return new MemberAssert(actual);
    }

    public MemberAssert hasEmail(String email)
    {
        isNotNull();

        Assertions.assertThat(actual.getEmail())
                .overridingErrorMessage("Expected email to be <%s> but was <%s>",
                        email,
                        actual.getEmail()
                )
                .isEqualTo(email);

        return this;
    }

    public MemberAssert hasFirstname(String firstname)
    {
        isNotNull();

        Assertions.assertThat(actual.getFirstname())
                .overridingErrorMessage("Expected first name to be <%s> but was <%s>",
                        firstname,
                        actual.getFirstname()
                )
                .isEqualTo(firstname);

        return this;
    }

    public MemberAssert hasSurname(String surname)
    {
        isNotNull();

        Assertions.assertThat(actual.getSurname())
                .overridingErrorMessage("Expected surname to be <%s> but was <%s>",
                        surname,
                        actual.getSurname()
                )
                .isEqualTo(surname);

        return this;
    }
}