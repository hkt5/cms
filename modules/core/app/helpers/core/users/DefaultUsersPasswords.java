package helpers.core.users;

import enums.core.users.DefaultUserEnum;
import org.mindrot.jbcrypt.BCrypt;

public class DefaultUsersPasswords {

    public static final String password = BCrypt.hashpw("P@ssw0ord", BCrypt.gensalt(12));

    public static String selectPassword(DefaultUserEnum defaultUserEnum){

        switch (defaultUserEnum) {

            default:
                return DefaultUsersPasswords.password;
        }
    }
}
