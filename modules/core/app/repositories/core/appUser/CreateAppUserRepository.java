package repositories.core.appUser;

import models.core.AppUser;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface CreateAppUserRepository {

    CompletionStage<Optional<AppUser>> createAppUser(AppUser appUser);
}
