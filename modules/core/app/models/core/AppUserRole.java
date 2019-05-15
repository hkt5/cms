package models.core;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class AppUserRole extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(30)
    public String appUserRoleName;

    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date appUserRoleCreatedAt;

    @OneToMany
    public AppUser appUser;

    public static Finder<Long, AppUserRole> FINDER = new Finder<Long, AppUserRole>(AppUserRole.class);

    public static List<AppUserRole> findAll(){

        return AppUserRole.FINDER.all();
    }

    public static AppUserRole findById(Long id){

        return AppUserRole.FINDER.byId(id);
    }

    public static AppUserRole findByName(String appUserRoleName){

        return AppUserRole.FINDER.query().where().eq("appUserRoleName", appUserRoleName).findOne();
    }
}
