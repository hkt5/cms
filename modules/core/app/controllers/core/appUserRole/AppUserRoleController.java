package controllers.core.appUserRole;

import com.fasterxml.jackson.databind.node.ObjectNode;
import play.libs.Json;
import play.libs.concurrent.HttpExecutionContext;
import play.mvc.Controller;
import play.mvc.Http;
import play.mvc.Result;
import repositories.core.appUserRoleRepository.AppUserRoleRepository;

import javax.inject.Inject;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;

@Singleton
public class AppUserRoleController extends Controller {

    private final AppUserRoleRepository appUserRoleRepository;
    private final HttpExecutionContext executionContext;

    @Inject
    public AppUserRoleController(AppUserRoleRepository appUserRoleRepository, HttpExecutionContext executionContext) {
        this.appUserRoleRepository = appUserRoleRepository;
        this.executionContext = executionContext;
    }

    public CompletionStage<Result> findAll(Http.Request request){

        return appUserRoleRepository.findAllAppUserRoles().thenApplyAsync(list -> {
            ObjectNode objectNode = Json.newObject();
            objectNode.put("success", "true");
            objectNode.set("roles", Json.toJson(list));
            return ok(objectNode);
        }, executionContext.current());
    }

    public CompletionStage<Result> findById(Http.Request request, Long id){

        return appUserRoleRepository.findAppUserRoleById(id).thenApplyAsync(optionalRole -> {
            ObjectNode objectNode = Json.newObject();
            objectNode.put("success", "true");
            optionalRole.ifPresent(role -> objectNode.set("role", Json.toJson(role)));
            return ok(objectNode);
        }, executionContext.current());
    }

    public CompletionStage<Result> findByName(Http.Request request, String name){

        return appUserRoleRepository.findAppUserRoleByName(name).thenApplyAsync(optionalRole -> {
            ObjectNode objectNode = Json.newObject();
            objectNode.put("success", "true");
            optionalRole.ifPresent(role -> objectNode.set("role", Json.toJson(role)));
            return ok(objectNode);
        }, executionContext.current());
    }
}
