package repositories.core.appUser;

import enums.core.users.DefaultUserEnum;
import helpers.core.users.*;
import io.ebean.Model;
import models.core.AppUser;
import models.core.AppUserRole;
import models.core.AppUserStatus;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import play.test.WithApplication;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;

public class AppUserRepositoryTest extends WithApplication {

    private final int expectedSize = 4;

    private final Long notExistsUserId = 5L;
    private final String notExistsUserName = "Jonathan Moore";
    private final String notExistsUserEmail = "jonathan@moore.com";
    private final String notExistsUserPhone = "000-000-005";

    private final String newUserName = "Susan Moore";
    private final String newUserEmail = "susan@moore.com";
    private final String newUserPhone = "000-000-006";

    private AppUser userToUpdate;

    @Before
    public void setup(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        appUserRepository.findAppUserById(DefaultUsersIds.selectUserId(DefaultUserEnum.JOHNDOE)).thenApplyAsync(
                optionalUser -> {
                    optionalUser.ifPresent(appUser -> userToUpdate = appUser);
                    return null;
                }
        );
    }

    @Test
    public void appUsersSizeTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<List<AppUser>> stage = appUserRepository.findAll();
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(list -> {

                    return !list.isEmpty() && list.size() == this.expectedSize;
                })
        );
    }

    @Test
    public void johnDoeIdTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserById(
                DefaultUsersIds.selectUserId(DefaultUserEnum.JOHNDOE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void janeDoeIdTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserById(
                DefaultUsersIds.selectUserId(DefaultUserEnum.JANEDOE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void johnRoeIdTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserById(
                DefaultUsersIds.selectUserId(DefaultUserEnum.JOHNROE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void janeRoeIdTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserById(
                DefaultUsersIds.selectUserId(DefaultUserEnum.JANEROE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void notExistsUserIdTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserById(this.notExistsUserId);
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }

    @Test
    public void johnDoeUserNameTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByUserName(
                DefaultUsersNames.selectUserName(DefaultUserEnum.JOHNDOE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void janeDoeUserNameTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByUserName(
                DefaultUsersNames.selectUserName(DefaultUserEnum.JANEDOE)
        );
        await().atMost(2, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void johnRoeUserNameTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByUserName(
                DefaultUsersNames.selectUserName(DefaultUserEnum.JOHNROE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void janeRoeNameTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByUserName(
                DefaultUsersNames.selectUserName(DefaultUserEnum.JANEROE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void testNotExistsUserNameTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByUserName(
                this.notExistsUserName
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }

    @Test
    public void johnDoeEmailTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByEmail(
                DefaultUsersEmails.selectUserEmail(DefaultUserEnum.JOHNDOE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void janeDoeEmailTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByEmail(
                DefaultUsersEmails.selectUserEmail(DefaultUserEnum.JANEDOE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void johnRoeEmailTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByEmail(
                DefaultUsersEmails.selectUserEmail(DefaultUserEnum.JOHNROE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void janeRoeEmailTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByEmail(
                DefaultUsersEmails.selectUserEmail(DefaultUserEnum.JANEROE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void notExistsUserEmailTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByUserName(this.notExistsUserEmail);
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }

    @Test
    public void johnDoePhoneTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByPhone(
                DefaultUsersPhones.selectUserPhone(DefaultUserEnum.JOHNDOE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void janeDoePhoneTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByPhone(
                DefaultUsersPhones.selectUserPhone(DefaultUserEnum.JANEDOE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void johnRoePhoneTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByPhone(
                DefaultUsersPhones.selectUserPhone(DefaultUserEnum.JOHNROE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void janeRoePhoneTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByPhone(
                DefaultUsersPhones.selectUserPhone(DefaultUserEnum.JANEROE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test
    public void notExistsPhoneTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByPhone(this.notExistsUserPhone);
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }

    @Test(expected = RuntimeException.class)
    public void createAppUserWithExistsUserNameTest(){

        AppUser appUser = new AppUser();
        appUser.appUserName = DefaultUsersNames.selectUserName(DefaultUserEnum.JOHNROE);
        appUser.appUserEmail = this.newUserEmail;
        appUser.appUserPhone = this.newUserPhone;
        appUser.appUserLoginPassword = DefaultUsersPasswords.selectPassword(DefaultUserEnum.JOHNDOE);
        appUser.appUserStatus = AppUserStatus.findByName("active");
        appUser.appUserRole = AppUserRole.findByName("user");

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.createAppUser(appUser);
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }

    @Test(expected = RuntimeException.class)
    public void createAppUserWithExistsEmailTest(){

        AppUser appUser = new AppUser();
        appUser.appUserName = this.newUserName;
        appUser.appUserEmail = DefaultUsersEmails.selectUserEmail(DefaultUserEnum.JOHNDOE);
        appUser.appUserPhone = this.newUserPhone;
        appUser.appUserLoginPassword = DefaultUsersPasswords.selectPassword(DefaultUserEnum.JOHNDOE);
        appUser.appUserStatus = AppUserStatus.findByName("active");
        appUser.appUserRole = AppUserRole.findByName("user");

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.createAppUser(appUser);
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }

    @Test(expected = RuntimeException.class)
    public void createAppUserWithExistsPhoneTest(){

        AppUser appUser = new AppUser();
        appUser.appUserName = this.newUserName;
        appUser.appUserEmail = this.newUserEmail;
        appUser.appUserPhone = DefaultUsersPhones.selectUserPhone(DefaultUserEnum.JOHNDOE);
        appUser.appUserLoginPassword = DefaultUsersPasswords.selectPassword(DefaultUserEnum.JOHNDOE);
        appUser.appUserStatus = AppUserStatus.findByName("active");
        appUser.appUserRole = AppUserRole.findByName("user");

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.createAppUser(appUser);
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }

    @Test
    public void createAppUserTest(){

        AppUser appUser = new AppUser();
        appUser.appUserName = this.newUserName;
        appUser.appUserEmail = this.newUserEmail;
        appUser.appUserPhone = this.newUserPhone;
        appUser.appUserLoginPassword = DefaultUsersPasswords.selectPassword(DefaultUserEnum.JOHNDOE);
        appUser.appUserStatus = AppUserStatus.findByName("active");
        appUser.appUserRole = AppUserRole.findByName("user");

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.createAppUser(appUser);
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @Test(expected = RuntimeException.class)
    public void updateUserWithExistsUserNameTest(){

        this.userToUpdate.appUserName = DefaultUsersNames.selectUserName(DefaultUserEnum.JANEDOE);

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.updateAppUser(
                this.userToUpdate, DefaultUsersIds.selectUserId(DefaultUserEnum.JOHNDOE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }

    @Test(expected = RuntimeException.class)
    public void updateUserWithExistsUserEmailTest(){

        this.userToUpdate.appUserEmail = DefaultUsersEmails.selectUserEmail(DefaultUserEnum.JANEDOE);

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.updateAppUser(
                this.userToUpdate, DefaultUsersIds.selectUserId(DefaultUserEnum.JOHNDOE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }

    @Test(expected = RuntimeException.class)
    public void updateUserWithExistsUserPhoneTest(){

        this.userToUpdate.appUserPhone = DefaultUsersPhones.selectUserPhone(DefaultUserEnum.JANEDOE);

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.updateAppUser(
                this.userToUpdate, DefaultUsersIds.selectUserId(DefaultUserEnum.JOHNDOE)
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isEmpty)
        );
    }

    @Test
    public void updateAppUserTest(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.updateAppUser(
                this.userToUpdate, this.userToUpdate.id
        );
        await().atMost(1, TimeUnit.SECONDS).until(
                () -> assertThat(stage.toCompletableFuture()).isCompletedWithValueMatching(Optional::isPresent)
        );
    }

    @After
    public void tearDown(){

        final AppUserRepository appUserRepository = app.injector().instanceOf(AppUserRepository.class);
        final CompletionStage<Optional<AppUser>> stage = appUserRepository.findAppUserByUserName(this.newUserName);
        stage.thenApplyAsync(
                user -> {

                    user.ifPresent(Model::delete);
                    return null;
                }
        );
    }
}