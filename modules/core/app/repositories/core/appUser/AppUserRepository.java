package repositories.core.appUser;

import io.ebean.Ebean;
import io.ebean.EbeanServer;
import models.core.AppUser;
import models.core.AppUserPassword;
import play.db.ebean.EbeanConfig;
import repositories.core.DatabaseExecutionContext;

import javax.inject.Inject;
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

                    try {

                        ebeanServer.save(appUser);
                        AppUserPassword appUserPassword = new AppUserPassword();
                        findAppUserByAllData(appUser).ifPresent(user -> appUserPassword.appUser = user);
                    } finally {

                        return Optional.of(appUser);
                    }
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

                    Optional<AppUser> optionalAppUser = ebeanServer.find(AppUser.class).setId(appUserId).findOneOrEmpty();
                    if(optionalAppUser.isEmpty()){
                        return Optional.empty();
                    }
                    try {
                        optionalAppUser.ifPresent(
                                user -> {
                                    if (appUser.appUserName != null) {
                                        user.appUserName = appUser.appUserName;
                                    }

                                    if (appUser.appUserEmail != null) {
                                        user.appUserEmail = appUser.appUserEmail;
                                    }
                                    if (appUser.appUserPhone != null) {
                                        user.appUserPhone = appUser.appUserPhone;
                                    }
                                    if (appUser.appUserPassword != null) {
                                        user.appUserPassword = appUser.appUserPassword;
                                    }
                                    if (appUser.appUserStatus != null) {
                                        user.appUserStatus = appUser.appUserStatus;
                                    }
                                    if (appUser.appUserRole != null) {
                                        user.appUserRole = appUser.appUserRole;
                                    }
                                    if (
                                            appUser.appUserLoginPassword != null &&
                                                    !appUser.appUserLoginPassword.equals(user.appUserLoginPassword)
                                    ) {
                                        user.appUserLoginPassword = appUser.appUserLoginPassword;
                                    }
                                    user.update();
                                }
                        );

                    } finally {

                        return optionalAppUser;
                    }
                }, executionContext
        );
    }
}
