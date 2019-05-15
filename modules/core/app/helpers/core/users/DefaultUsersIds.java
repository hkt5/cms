package helpers.core.users;

import enums.core.users.DefaultUserEnum;

public class DefaultUsersIds {

    public static final Long johnDoeId = 1L;
    public static final Long janeDoeId = 2L;
    public static final Long johnRoeId = 3L;
    public static final Long janeRoeId = 4L;

    public static Long selectUserId(DefaultUserEnum defaultUserEnum){

        switch (defaultUserEnum){
            case JOHNDOE:
                return DefaultUsersIds.johnDoeId;
            case JANEDOE:
                return DefaultUsersIds.janeDoeId;
            case JOHNROE:
                return DefaultUsersIds.johnRoeId;
            case JANEROE:
                return DefaultUsersIds.janeRoeId;
            default:
                return null;
        }
    }
}
