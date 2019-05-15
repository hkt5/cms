package helpers.core.users;

import enums.core.users.DefaultUserEnum;
import models.core.AppUserRole;
import repositories.core.appUserRoleRepository.AppUserRoleRepository;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class DefaultUsersRoles {

    public static final String johnDoeRoleName = "user";
    public static final String janeDoeRoleName = "customer";
    public static final String johnRoeRoleName = "employee";
    public static final String janeRoeRoleName = "admin";

    private final AppUserRoleRepository appUserRoleRepository;

    @Inject
    public DefaultUsersRoles(AppUserRoleRepository appUserRoleRepository) {
        this.appUserRoleRepository = appUserRoleRepository;
    }

    public CompletionStage<Optional<AppUserRole>> selectRole(DefaultUserEnum defaultUserEnum){

        switch (defaultUserEnum) {

            case JOHNDOE:
                return appUserRoleRepository.findAppUserRoleByName(DefaultUsersRoles.johnDoeRoleName);
            case JANEDOE:
                return appUserRoleRepository.findAppUserRoleByName(DefaultUsersRoles.janeDoeRoleName);
            case JOHNROE:
                return appUserRoleRepository.findAppUserRoleByName(DefaultUsersRoles.johnRoeRoleName);
            case JANEROE:
                return appUserRoleRepository.findAppUserRoleByName(DefaultUsersRoles.janeRoeRoleName);
            default:
                return null;
        }
    }
}
