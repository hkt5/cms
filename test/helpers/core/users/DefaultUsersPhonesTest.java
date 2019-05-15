package helpers.core.users;

import enums.core.users.DefaultUserEnum;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultUsersPhonesTest {

    @Test
    public void johnDoePhoneTest()
    {

        assertEquals(DefaultUsersPhones.johnDoePhone, DefaultUsersPhones.selectUserPhone(DefaultUserEnum.JOHNDOE));
    }

    @Test
    public void janeDoePhoneTest()
    {

        assertEquals(DefaultUsersPhones.janeDoePhone, DefaultUsersPhones.selectUserPhone(DefaultUserEnum.JANEDOE));
    }

    @Test
    public void johnRoePhoneTest()
    {

        assertEquals(DefaultUsersPhones.johnRoePhone, DefaultUsersPhones.selectUserPhone(DefaultUserEnum.JOHNROE));
    }

    @Test
    public void janeRoePhoneTest()
    {

        assertEquals(DefaultUsersPhones.janeRoePhone, DefaultUsersPhones.selectUserPhone(DefaultUserEnum.JANEROE));
    }
}