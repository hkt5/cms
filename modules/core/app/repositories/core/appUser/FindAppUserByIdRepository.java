package repositories.core.appUser;

import models.core.AppUser;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface FindAppUserByIdRepository {

    CompletionStage<Optional<AppUser>> findAppUserById(Long id);
}
