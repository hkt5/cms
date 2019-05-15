package repositories.core.appUserStatusRepository;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import models.core.AppUserStatus;
import play.db.ebean.EbeanConfig;
import repositories.core.DatabaseExecutionContext;

import javax.inject.Inject;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AppUserStatusRepository implements FindAllAppUserStatusesRepository, FindAppUserStatusByIdRepository,
        FindAppUserStatusByNameRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public AppUserStatusRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<List<AppUserStatus>> findAllAppUserStatuses() {

        return CompletableFuture.supplyAsync(
                () -> ebeanServer.find(AppUserStatus.class).findList(), executionContext
        );
    }


    @Override
    public CompletionStage<Optional<AppUserStatus>> findAppUserStatusById(Long id) {

        return CompletableFuture.supplyAsync(
                () -> ebeanServer.find(AppUserStatus.class).setId(id).findOneOrEmpty(), executionContext
        );
    }


    @Override
    public CompletionStage<Optional<AppUserStatus>> findAppUserStatusByName(String appUserStatusName) {

        return CompletableFuture.supplyAsync(
                () -> ebeanServer.find(AppUserStatus.class).where()
                        .eq("appUserStatusName", appUserStatusName).findOneOrEmpty(),
                executionContext
        );
    }
}
