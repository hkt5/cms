package helpers.core.users;

import enums.core.users.DefaultUserEnum;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultUsersPasswordsTest {

    @Test
    public void johnDoePasswordTest()
    {

        assertEquals(DefaultUsersPasswords.password, DefaultUsersPasswords.selectPassword(DefaultUserEnum.JOHNDOE));
    }

    @Test
    public void janeDoePasswordTest()
    {

        assertEquals(DefaultUsersPasswords.password, DefaultUsersPasswords.selectPassword(DefaultUserEnum.JANEDOE));
    }

    @Test
    public void johnRoePasswordTest()
    {

        assertEquals(DefaultUsersPasswords.password, DefaultUsersPasswords.selectPassword(DefaultUserEnum.JOHNROE));
    }

    @Test
    public void janeRoePasswordTest()
    {

        assertEquals(DefaultUsersPasswords.password, DefaultUsersPasswords.selectPassword(DefaultUserEnum.JANEROE));
    }
}