package repositories.core.appUser;

import models.core.AppUser;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface FindAppUserByEmailRepository {

    CompletionStage<Optional<AppUser>> findAppUserByEmail(String appUserEmail);
}
