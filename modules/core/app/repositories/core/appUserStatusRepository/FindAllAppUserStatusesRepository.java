package repositories.core.appUserStatusRepository;

import models.core.AppUserStatus;

import java.util.List;
import java.util.concurrent.CompletionStage;

public interface FindAllAppUserStatusesRepository {

    CompletionStage<List<AppUserStatus>> findAllAppUserStatuses();
}
