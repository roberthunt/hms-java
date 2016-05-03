package uk.org.nottinghack.assertion;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import uk.org.nottinghack.domain.dto.MemberDto;

/**
 * @author <a href="rob.hunt@nottinghack.org.uk">Robert Hunt</a>
 */
public class MemberDtoMatcher
{
    public static Matcher<MemberDto> hasFirstname(final String firstname)
    {
        return new TypeSafeMatcher<MemberDto>()
        {
            @Override
            protected boolean matchesSafely(MemberDto memberDto)
            {
                return firstname.equals(memberDto.getFirstname());
            }

            @Override
            public void describeTo(Description description)
            {
                description.appendText("firstname to be ").appendValue(firstname);
            }

            @Override
            protected void describeMismatchSafely(MemberDto item, Description mismatchDescription)
            {
                mismatchDescription.appendText("was ").appendValue(item.getFirstname());
            }
        };
    }

    public static Matcher<MemberDto> hasUnlockText(final String unlockText)
    {
        return new TypeSafeMatcher<MemberDto>()
        {
            @Override
            protected boolean matchesSafely(MemberDto memberDto)
            {
                return unlockText.equals(memberDto.getUnlockText());
            }

            @Override
            public void describeTo(Description description)
            {
                description.appendText("unlockText to be ").appendValue(unlockText);
            }

            @Override
            public void describeMismatchSafely(MemberDto item, Description mismatchDescription)
            {
                mismatchDescription.appendText("was ").appendValue(item.getUnlockText());
            }
        };
    }
}
