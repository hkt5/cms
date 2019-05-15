package helpers.core.users;

import enums.core.users.DefaultUserEnum;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultUsersNamesTest {

    @Test
    public void johnDoeNameTest(){

        assertEquals(DefaultUsersNames.johnDoeName, DefaultUsersNames.selectUserName(DefaultUserEnum.JOHNDOE));
    }

    @Test
    public void janeDoeNameTest(){

        assertEquals(DefaultUsersNames.janeDoeName, DefaultUsersNames.selectUserName(DefaultUserEnum.JANEDOE));
    }

    @Test
    public void johnRoeNameTest(){

        assertEquals(DefaultUsersNames.johnRoeName, DefaultUsersNames.selectUserName(DefaultUserEnum.JOHNROE));
    }

    @Test
    public void janeRoeNameTest(){

        assertEquals(DefaultUsersNames.janeRoeName, DefaultUsersNames.selectUserName(DefaultUserEnum.JANEROE));
    }
}