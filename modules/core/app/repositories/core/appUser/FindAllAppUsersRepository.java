package repositories.core.appUser;

import models.core.AppUser;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface FindAllAppUsersRepository {

    CompletionStage<List<AppUser>> findAll();
}
