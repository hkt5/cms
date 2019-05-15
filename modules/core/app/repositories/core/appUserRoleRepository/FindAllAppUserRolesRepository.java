package repositories.core.appUserRoleRepository;

import models.core.AppUserRole;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface FindAllAppUserRolesRepository {

    CompletionStage<List<AppUserRole>> findAllAppUserRoles();
}
