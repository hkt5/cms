package helpers.core.users;

import enums.core.users.DefaultUserEnum;
import org.junit.Test;

import static org.junit.Assert.*;

public class DefaultUsersIdsTest {

    @Test
    public void johnDoeIdTest(){

        assertEquals(DefaultUsersIds.johnDoeId, DefaultUsersIds.selectUserId(DefaultUserEnum.JOHNDOE));
    }

    @Test
    public void janeDoeIdTest(){

        assertEquals(DefaultUsersIds.janeDoeId, DefaultUsersIds.selectUserId(DefaultUserEnum.JANEDOE));
    }

    @Test
    public void johnRoeIdTest(){

        assertEquals(DefaultUsersIds.johnRoeId, DefaultUsersIds.selectUserId(DefaultUserEnum.JOHNROE));
    }

    @Test
    public void janeRoeIdTest(){

        assertEquals(DefaultUsersIds.janeRoeId, DefaultUsersIds.selectUserId(DefaultUserEnum.JANEROE));
    }
}