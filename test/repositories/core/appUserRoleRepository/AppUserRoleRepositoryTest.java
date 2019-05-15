package repositories.core.appUserRoleRepository;

import models.core.AppUserRole;
import org.junit.Test;
import play.Application;
import play.inject.guice.GuiceApplicationBuilder;
import play.test.WithApplication;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;

import static java.util.concurrent.TimeUnit.SECONDS;
import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.junit.Assert.*;

public class AppUserRoleRepositoryTest extends WithApplication {

    private final int rolesSize = 5;

    private final Long guestId = 1L;
    private final Long userId = 2L;
    private final Long customerId = 3L;
    private final Long employeeId = 4L;
    private final Long adminId = 5L;
    private final Long notExistsId = 0L;

    private final String guestName = "guest";
    private final String userName = "user";
    private final String customerName = "customer";
    private final String employeeName = "employee";
    private final String adminName = "admin";
    private final String notExistsRoleName = "not_exists_role_name";

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testFindAllUserRoles(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<List<AppUserRole>> stage = appUserRoleRepository.findAllAppUserRoles();
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(list -> list.size() == rolesSize);
        });
    }

    @Test
    public void testFindRoleByGuestRoleId(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository.findAppUserRoleById(guestId);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent);
        });
    }

    @Test
    public void testFindRoleByUserRoleId(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository.findAppUserRoleById(userId);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent);
        });
    }

    @Test
    public void testFindRoleByCustomerRoleId(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository.findAppUserRoleById(customerId);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent);
        });
    }

    @Test
    public void testFindRoleByEmployeeRoleId(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository.findAppUserRoleById(employeeId);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent);
        });
    }

    @Test
    public void testFindRoleByAdminRoleId(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository.findAppUserRoleById(adminId);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent);
        });
    }

    @Test
    public void testFindRoleByNotExistsRoleId(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository.findAppUserRoleById(notExistsId);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty);
        });
    }

    @Test
    public void testFindRoleByGuestName(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository.findAppUserRoleByName(guestName);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent);
        });
    }

    @Test
    public void testFindRoleByUserName(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository.findAppUserRoleByName(userName);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent);
        });
    }

    @Test
    public void testFindRoleByCustomerName(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository.findAppUserRoleByName(customerName);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent);
        });
    }

    @Test
    public void testFindRoleByEmployeeName(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository.findAppUserRoleByName(employeeName);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent);
        });
    }

    @Test
    public void testFindRoleByAdminName(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository.findAppUserRoleByName(adminName);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent);
        });
    }

    @Test
    public void testFindRoleByNotExistsRoleName(){

        final AppUserRoleRepository appUserRoleRepository = app.injector().instanceOf(AppUserRoleRepository.class);
        final CompletionStage<Optional<AppUserRole>> stage = appUserRoleRepository
                .findAppUserRoleByName(notExistsRoleName);
        await().atMost(1, SECONDS).until(() -> {
            assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty);
        });
    }
}