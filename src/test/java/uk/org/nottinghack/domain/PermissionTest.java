package uk.org.nottinghack.domain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import uk.org.nottinghack.Application;
import uk.org.nottinghack.repository.MemberRepository;

import javax.transaction.Transactional;

/**
 * Created by Rob on 13/06/2015.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@Transactional
public class PermissionTest
{
    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void testPermission()
    {
        Member member = memberRepository.findOne(1);
    }
}
