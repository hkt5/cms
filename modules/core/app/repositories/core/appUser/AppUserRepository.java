package repositories.core.appUser;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import io.ebean.Transaction;
import models.core.AppUser;
import models.core.AppUserPassword;
import play.db.ebean.EbeanConfig;
import repositories.core.DatabaseExecutionContext;

import javax.inject.Inject;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

public class AppUserRepository implements FindAllAppUsersRepository, FindAppUserByIdRepository,
        FindAppUserByUserNameRepository, FindAppUserByEmailRepository, FindAppUserByUserPhoneRepository,
        CreateAppUserRepository, UpdateAppUserRepository {

    private final EbeanServer ebeanServer;
    private final DatabaseExecutionContext executionContext;

    @Inject
    public AppUserRepository(EbeanConfig ebeanConfig, DatabaseExecutionContext executionContext) {
        this.ebeanServer = Ebean.getServer(ebeanConfig.defaultServer());
        this.executionContext = executionContext;
    }

    @Override
    public CompletionStage<List<AppUser>> findAll() {

        return CompletableFuture.supplyAsync(
                () -> ebeanServer.find(AppUser.class).fetch("appUserStatus").fetch("appUserRole")
                        .findList(),
                executionContext
        );
    }

    @Override
    public CompletionStage<Optional<AppUser>> findAppUserById(Long id) {

        return CompletableFuture.supplyAsync(
                () -> ebeanServer.find(AppUser.class).setId(id).fetch("appUserStatus").fetch("appUserRole")
                        .findOneOrEmpty(),
                executionContext
        );
    }

    @Override
    public CompletionStage<Optional<AppUser>> findAppUserByUserName(String appUserName) {

        return CompletableFuture.supplyAsync(
                () -> ebeanServer.find(AppUser.class).fetch("appUserStatus").fetch("appUserRole").where()
                        .eq("appUserName", appUserName).findOneOrEmpty(),
                executionContext
        );
    }

    @Override
    public CompletionStage<Optional<AppUser>> findAppUserByEmail(String appUserEmail) {

        return CompletableFuture.supplyAsync(
                () -> ebeanServer.find(AppUser.class).fetch("appUserStatus").fetch("appUserRole").where()
                        .eq("appUserEmail", appUserEmail).findOneOrEmpty(),
                executionContext
        );
    }

    @Override
    public CompletionStage<Optional<AppUser>> findAppUserByPhone(String appUserPhone) {

        return CompletableFuture.supplyAsync(
                () -> ebeanServer.find(AppUser.class).fetch("appUserStatus").fetch("appUserRole").where()
                        .eq("appUserPhone", appUserPhone).findOneOrEmpty(),
                executionContext
        );
    }

    @Override
    public CompletionStage<Optional<AppUser>> createAppUser(AppUser appUser) {

        return CompletableFuture.supplyAsync(
                () -> {

                    ebeanServer.save(appUser);
                    Optional<AppUser> appUserOptional = findAppUserByAllData(appUser);
                    AppUserPassword appUserPassword = new AppUserPassword();
                    appUserOptional.ifPresent(currentUser -> appUserPassword.appUser = currentUser);
                    appUserPassword.appUserPasswordCreatedAt = new Date();
                    appUserPassword.appUserPasswordUpdatedAt = new Date();
                    ebeanServer.save(appUserPassword);

                    return ebeanServer.find(AppUser.class).fetch("appUserStatus").fetch("appUserRole").where()
                            .eq("appUserName", appUser.appUserName)
                            .eq("appUserEmail", appUser.appUserEmail)
                            .eq("appUserPhone", appUser.appUserPhone).findOneOrEmpty();
                }, executionContext
        );
    }

    private Optional<AppUser> findAppUserByAllData(AppUser appUser){

        return ebeanServer.find(AppUser.class).fetch("appUserStatus").fetch("appUserRole").where()
                .eq("appUserName", appUser.appUserName)
                .eq("appUserEmail", appUser.appUserEmail)
                .eq("appUserPhone", appUser.appUserPhone)
                .eq("appUserStatus", appUser.appUserStatus)
                .eq("appUserRole", appUser.appUserRole).findOneOrEmpty();
    }

    @Override
    public CompletionStage<Optional<AppUser>> updateAppUser(AppUser appUser, Long appUserId) {

        return CompletableFuture.supplyAsync(
                () -> {

                    Transaction txn = ebeanServer.beginTransaction();
                    try {

                        Optional<AppUser> optionalAppUser = ebeanServer.find(AppUser.class).setId(appUserId)
                                .fetch("appUserPassword").fetch("appUserRole").fetch("appUserStatus").findOneOrEmpty();
                        if(optionalAppUser.isEmpty()){
                            throw new Exception("Current user is empty!");
                        }
                        AppUser currentUser = optionalAppUser.get();
                        if(currentUser.appUserLoginPassword.equals(appUser.appUserLoginPassword)){
                            appUser.appUserUpdatedAt = new Date();
                            ebeanServer.update(appUser.appUserLoginPassword);
                        }
                        ebeanServer.update(appUser);
                    } catch (Exception e) {
                        e.printStackTrace();
                    } finally {

                        txn.end();
                    }

                    return findAppUserByAllData(appUser);
                }, executionContext
        );
    }
}
