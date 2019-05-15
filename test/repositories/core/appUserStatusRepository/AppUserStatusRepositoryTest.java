package repositories.core.appUserStatusRepository;

import models.core.AppUserStatus;
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

public class AppUserStatusRepositoryTest extends WithApplication {

    private final int expectedSize = 2;

    private final Long activeStatusId = 1L;
    private final Long inactiveStatusId = 2L;
    private final Long notExistsStatusId = 3L;

    private final String activeStatusName = "active";
    private final String inactiveStatusName = "inactive";
    private final String notExistsStatusName = "not_exists_status_name";

    @Override
    protected Application provideApplication() {
        return new GuiceApplicationBuilder().build();
    }

    @Test
    public void testAppUserStatusesSize() {

        final AppUserStatusRepository appUserStatusRepository = app.injector().instanceOf(AppUserStatusRepository.class);
        final CompletionStage<List<AppUserStatus>> stage = appUserStatusRepository.findAllAppUserStatuses();
        await().atMost(1, SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(list -> list.size() == expectedSize)
        );
    }

    @Test
    public void testFindActiveStatusById() {

        final AppUserStatusRepository appUserStatusRepository = app.injector().instanceOf(AppUserStatusRepository.class);
        final CompletionStage<Optional<AppUserStatus>> stage = appUserStatusRepository.findAppUserStatusById(activeStatusId);
        await().atMost(1, SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void testFindInActiveStatusById() {

        final AppUserStatusRepository appUserStatusRepository = app.injector().instanceOf(AppUserStatusRepository.class);
        final CompletionStage<Optional<AppUserStatus>> stage = appUserStatusRepository.findAppUserStatusById(inactiveStatusId);
        await().atMost(1, SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void testFindActiveStatusByNotExitsId(){

        final AppUserStatusRepository appUserStatusRepository = app.injector().instanceOf(AppUserStatusRepository.class);
        final CompletionStage<Optional<AppUserStatus>> stage = appUserStatusRepository.findAppUserStatusById(notExistsStatusId);
        await().atMost(1, SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }

    @Test
    public void testFindActiveStatusByName() {

        final AppUserStatusRepository appUserStatusRepository = app.injector().instanceOf(AppUserStatusRepository.class);
        final CompletionStage<Optional<AppUserStatus>> stage = appUserStatusRepository.findAppUserStatusByName(activeStatusName);
        await().atMost(1, SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void testFindInActiveStatusByName() {

        final AppUserStatusRepository appUserStatusRepository = app.injector().instanceOf(AppUserStatusRepository.class);
        final CompletionStage<Optional<AppUserStatus>> stage = appUserStatusRepository.findAppUserStatusByName(inactiveStatusName);
        await().atMost(1, SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void testFindActiveStatusByNotExitsName(){

        final AppUserStatusRepository appUserStatusRepository = app.injector().instanceOf(AppUserStatusRepository.class);
        final CompletionStage<Optional<AppUserStatus>> stage = appUserStatusRepository.findAppUserStatusByName(notExistsStatusName);
        await().atMost(1, SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }
}