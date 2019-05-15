package helpers.core.users;

import enums.core.users.DefaultUserEnum;
import models.core.AppUserStatus;
import repositories.core.appUserStatusRepository.AppUserStatusRepository;

import javax.inject.Inject;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

public class DefaultUsersStatuses {

    public static final String userStatus = "active";
    private final AppUserStatusRepository appUserStatusRepository;

    @Inject
    public DefaultUsersStatuses(AppUserStatusRepository appUserStatusRepository) {
        this.appUserStatusRepository = appUserStatusRepository;
    }

    public CompletionStage<Optional<AppUserStatus>> selectUserStatus(DefaultUserEnum defaultUserEnum)
    {

        switch (defaultUserEnum) {

            default:
                return this.appUserStatusRepository.findAppUserStatusByName(DefaultUsersStatuses.userStatus);
        }

    }
}
