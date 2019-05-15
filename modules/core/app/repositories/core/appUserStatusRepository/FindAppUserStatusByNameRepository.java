package repositories.core.appUserStatusRepository;

import models.core.AppUserStatus;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface FindAppUserStatusByNameRepository {

    CompletionStage<Optional<AppUserStatus>> findAppUserStatusByName(String appUserStatusName);
}
