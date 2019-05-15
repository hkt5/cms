package repositories.core.appUserRoleRepository;

import models.core.AppUserRole;

import java.util.Optional;
import java.util.concurrent.CompletionStage;

public interface FindAppUserRoleByIdRepository {

    CompletionStage<Optional<AppUserRole>> findAppUserRoleById(Long id);
}
