package helpers.core.users;

import enums.core.users.DefaultUserEnum;

public class DefaultUsersNames {

    public static String johnDoeName = "John Doe";
    public static String janeDoeName = "Jane Doe";
    public static String johnRoeName = "John Roe";
    public static String janeRoeName = "Jane Roe";

    public static String selectUserName(DefaultUserEnum defaultUserEnum){

        switch (defaultUserEnum){
            case JOHNDOE:
                return DefaultUsersNames.johnDoeName;
            case JANEDOE:
                return DefaultUsersNames.janeDoeName;
            case JOHNROE:
                return DefaultUsersNames.johnRoeName;
            case JANEROE:
                return DefaultUsersNames.janeRoeName;
            default:
                return null;
        }
    }
}
