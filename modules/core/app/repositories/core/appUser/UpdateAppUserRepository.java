package repositories.core.appUser;

import models.core.AppUser;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface UpdateAppUserRepository {

    CompletionStage<Optional<AppUser>> updateAppUser(AppUser appUse, Long appUserId);
}
