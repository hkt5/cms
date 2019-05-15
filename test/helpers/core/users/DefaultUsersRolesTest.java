package helpers.core.users;

import enums.core.users.DefaultUserEnum;
import models.core.AppUserRole;
import org.junit.Test;
import play.test.WithApplication;

import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class DefaultUsersRolesTest extends WithApplication {

    @Test
    public void johnDoeRoleTest()
    {

        final DefaultUsersRoles defaultUsersRoles = app.injector().instanceOf(DefaultUsersRoles.class);
        final CompletionStage<Optional<AppUserRole>> stage = defaultUsersRoles.selectRole(DefaultUserEnum.JOHNDOE);
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(role -> {

                    return role.isPresent() && role.get().appUserRoleName.equals(DefaultUsersRoles.johnDoeRoleName);
                })
        );
    }

    @Test
    public void janeDoeRoleTest()
    {

        final DefaultUsersRoles defaultUsersRoles = app.injector().instanceOf(DefaultUsersRoles.class);
        final CompletionStage<Optional<AppUserRole>> stage = defaultUsersRoles.selectRole(DefaultUserEnum.JANEDOE);
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(role -> {

                    return role.isPresent() && role.get().appUserRoleName.equals(DefaultUsersRoles.janeDoeRoleName);
                })
        );
    }

    @Test
    public void johnRoeRoleTest()
    {

        final DefaultUsersRoles defaultUsersRoles = app.injector().instanceOf(DefaultUsersRoles.class);
        final CompletionStage<Optional<AppUserRole>> stage = defaultUsersRoles.selectRole(DefaultUserEnum.JOHNROE);
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(role -> {

                    return role.isPresent() && role.get().appUserRoleName.equals(DefaultUsersRoles.johnRoeRoleName);
                })
        );
    }

    @Test
    public void janeRoeRoleTest()
    {

        final DefaultUsersRoles defaultUsersRoles = app.injector().instanceOf(DefaultUsersRoles.class);
        final CompletionStage<Optional<AppUserRole>> stage = defaultUsersRoles.selectRole(DefaultUserEnum.JANEROE);
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(role -> {

                    return role.isPresent() && role.get().appUserRoleName.equals(DefaultUsersRoles.janeRoeRoleName);
                })
        );
    }
}