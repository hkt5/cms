package helpers.core.users;

import enums.core.users.DefaultUserEnum;

public class DefaultUsersPhones {

    public static final String johnDoePhone = "000-000-001";
    public static final String janeDoePhone = "000-000-002";
    public static final String johnRoePhone = "000-000-003";
    public static final String janeRoePhone = "000-000-004";

    public static String selectUserPhone(DefaultUserEnum defaultUserEnum){

        switch (defaultUserEnum) {

            case JOHNDOE:
                return DefaultUsersPhones.johnDoePhone;
            case JANEDOE:
                return DefaultUsersPhones.janeDoePhone;
            case JOHNROE:
                return DefaultUsersPhones.johnRoePhone;
            case JANEROE:
                return DefaultUsersPhones.janeRoePhone;
            default:
                return null;
        }
    }
}
