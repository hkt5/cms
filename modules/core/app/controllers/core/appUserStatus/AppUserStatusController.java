package controllers.core.appUserStatus;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repositories.core.appUserStatusRepository.AppUserStatusRepository;
import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;

@Singleton
public class AppUserStatusController extends Controller {

    private final AppUserStatusRepository appUserStatusRepository;
    private final HttpExecutionContext executionContext;

    @Inject
    public AppUserStatusController(
            AppUserStatusRepository appUserStatusRepository, HttpExecutionContext executionContext
    ) {
        this.appUserStatusRepository = appUserStatusRepository;
        this.executionContext = executionContext;
    }

    public CompletionStage<Result> findAll(Http.Request request) {
        return appUserStatusRepository.findAllAppUserStatuses().thenApplyAsync(list -> {
            ObjectNode objectNode = Json.newObject();
            objectNode.put("success", "true");
            objectNode.set("statuses", Json.toJson(list));
            return ok(objectNode);
        }, executionContext.current());
    }

    public CompletionStage<Result> findById(Http.Request request, Long id){

        return appUserStatusRepository.findAppUserStatusById(id).thenApplyAsync(
                optionalStatus -> {
                    ObjectNode objectNode = Json.newObject();
                    objectNode.put("success", "true");
                    optionalStatus.ifPresent(appUserStatus -> objectNode.set("status", Json.toJson(appUserStatus)));
                    return ok(objectNode);
                }, executionContext.current()
        );
    }

    public CompletionStage<Result> findByName(Http.Request request, String name){

        return appUserStatusRepository.findAppUserStatusByName(name).thenApplyAsync(
                optionalStatus -> {

                    ObjectNode objectNode = Json.newObject();
                    objectNode.put("success", "true");
                    optionalStatus.ifPresent(appUserStatus -> objectNode.set("status", Json.toJson(appUserStatus)));
                    return ok(objectNode);
                }, executionContext.current()
        );
    }
}
