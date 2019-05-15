package helpers.core.users;

import enums.core.users.DefaultUserEnum;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultUsersEmailsTest {

    @Test
    public void johnDoeEmailTest()
    {

        assertEquals(DefaultUsersEmails.johnDoeEmail, DefaultUsersEmails.selectUserEmail(DefaultUserEnum.JOHNDOE));
    }

    @Test
    public void janeDoeEmailTest()
    {

        assertEquals(DefaultUsersEmails.janeDoeEmail, DefaultUsersEmails.selectUserEmail(DefaultUserEnum.JANEDOE));
    }

    @Test
    public void johnRoeEmailTest()
    {

        assertEquals(DefaultUsersEmails.johnRoeEmail, DefaultUsersEmails.selectUserEmail(DefaultUserEnum.JOHNROE));
    }

    @Test
    public void janeRoeEmailTest()
    {

        assertEquals(DefaultUsersEmails.janeRoeEmail, DefaultUsersEmails.selectUserEmail(DefaultUserEnum.JANEROE));
    }
}