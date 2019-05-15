package repositories.core.appUserRoleRepository;

import models.core.AppUserRole;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface FindAppUserRoleByNameRepository {

    CompletionStage<Optional<AppUserRole>> findAppUserRoleByName(String appUserRoleName);
}
