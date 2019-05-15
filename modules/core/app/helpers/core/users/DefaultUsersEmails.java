package helpers.core.users;

import enums.core.users.DefaultUserEnum;

public class DefaultUsersEmails {

    public static final String johnDoeEmail = "john@doe.com";
    public static final String janeDoeEmail = "jane@doe.com";
    public static final String johnRoeEmail = "john@roe.com";
    public static final String janeRoeEmail = "jane@roe.com";

    public static String selectUserEmail(DefaultUserEnum defaultUserEnum)
    {

        switch (defaultUserEnum) {

            case JOHNDOE:
                return DefaultUsersEmails.johnDoeEmail;
            case JANEDOE:
                return DefaultUsersEmails.janeDoeEmail;
            case JOHNROE:
                return DefaultUsersEmails.johnRoeEmail;
            case JANEROE:
                return DefaultUsersEmails.janeRoeEmail;
            default:
                return null;
        }
    }
}
