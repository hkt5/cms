package helpers.core.users;

import enums.core.users.DefaultUserEnum;
import models.core.AppUserStatus;
import org.junit.Test;
import play.test.WithApplication;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class DefaultUsersStatusesTest extends WithApplication {

    @Test
    public void johnDoeStatusTest()
    {

        final DefaultUsersStatuses defaultUsersStatuses = app.injector().instanceOf(DefaultUsersStatuses.class);
        final CompletionStage<Optional<AppUserStatus>> stage = defaultUsersStatuses.selectUserStatus(DefaultUserEnum.JOHNDOE);
        await().atMost(1, TimeUnit.SECONDS).until(() -> assertThat(
                stage.toCompletableFuture()).isCompletedWithValueMatching(
                        status -> {
                            return status.isPresent() && status.get().appUserStatusName.equals(
                                    DefaultUsersStatuses.userStatus
                            );
                        }
                )
        );
    }

    @Test
    public void janeDoeStatusTest()
    {

        final DefaultUsersStatuses defaultUsersStatuses = app.injector().instanceOf(DefaultUsersStatuses.class);
        final CompletionStage<Optional<AppUserStatus>> stage = defaultUsersStatuses.selectUserStatus(DefaultUserEnum.JANEDOE);
        await().atMost(1, TimeUnit.SECONDS).until(() -> assertThat(
                stage.toCompletableFuture()).isCompletedWithValueMatching(
                status -> {
                    return status.isPresent() && status.get().appUserStatusName.equals(
                            DefaultUsersStatuses.userStatus
                    );
                }
                )
        );
    }

    @Test
    public void johnRoeStatusTest()
    {

        final DefaultUsersStatuses defaultUsersStatuses = app.injector().instanceOf(DefaultUsersStatuses.class);
        final CompletionStage<Optional<AppUserStatus>> stage = defaultUsersStatuses.selectUserStatus(DefaultUserEnum.JOHNROE);
        await().atMost(1, TimeUnit.SECONDS).until(() -> assertThat(
                stage.toCompletableFuture()).isCompletedWithValueMatching(
                status -> {
                    return status.isPresent() && status.get().appUserStatusName.equals(
                            DefaultUsersStatuses.userStatus
                    );
                }
                )
        );
    }

    @Test
    public void janeRoeStatusTest()
    {

        final DefaultUsersStatuses defaultUsersStatuses = app.injector().instanceOf(DefaultUsersStatuses.class);
        final CompletionStage<Optional<AppUserStatus>> stage = defaultUsersStatuses.selectUserStatus(DefaultUserEnum.JANEROE);
        await().atMost(1, TimeUnit.SECONDS).until(() -> assertThat(
                stage.toCompletableFuture()).isCompletedWithValueMatching(
                status -> {
                    return status.isPresent() && status.get().appUserStatusName.equals(
                            DefaultUsersStatuses.userStatus
                    );
                }
                )
        );
    }
}