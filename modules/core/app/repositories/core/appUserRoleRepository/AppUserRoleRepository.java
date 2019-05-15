package repositories.core.appUserRoleRepository;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import models.core.AppUserRole;
import play.db.ebean.EbeanConfig;
import repositories.core.DatabaseExecutionContext;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AppUserRoleRepository implements FindAllAppUserRolesRepository, FindAppUserRoleByIdRepository,
        FindAppUserRoleByNameRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public AppUserRoleRepository(
            EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext
    ) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<List<AppUserRole>> findAllAppUserRoles() {
        return CompletableFuture.supplyAsync(() -> ebeanServer.find(AppUserRole.class).findList(), executionContext);
    }

    @Override
    public CompletionStage<Optional<AppUserRole>> findAppUserRoleById(Long id) {
        return CompletableFuture.supplyAsync(
                () -> ebeanServer.find(AppUserRole.class).setId(id).findOneOrEmpty(), executionContext
        );
    }

    @Override
    public CompletionStage<Optional<AppUserRole>> findAppUserRoleByName(String appUserRoleName) {
        return CompletableFuture.supplyAsync(
                () -> ebeanServer.find(AppUserRole.class).where().eq("appUserRoleName", appUserRoleName)
                        .findOneOrEmpty(),
                executionContext
        );
    }
}
