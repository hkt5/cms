package repositories.core.appUserStatusRepository;

import models.core.AppUserStatus;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface FindAppUserStatusByIdRepository {

    CompletionStage<Optional<AppUserStatus>> findAppUserStatusById(Long id);
}
