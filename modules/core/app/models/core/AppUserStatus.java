package models.core;

import io.ebean.Finder;
import io.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class AppUserStatus extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MinLength(3)
    @Constraints.MaxLength(50)
    public String appUserStatusName;

    @Column(nullable = false)
    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date appUserStatusCreatedAt;

    @OneToMany
    public AppUser appUser;

    public static Finder<Long, AppUserStatus> FINDER = new Finder<Long, AppUserStatus>(AppUserStatus.class);

    public static List<AppUserStatus> findAll(){

        return AppUserStatus.FINDER.all();
    }

    public static AppUserStatus findById(Long id){

        return AppUserStatus.FINDER.byId(id);
    }

    public static AppUserStatus findByName(String appUserStatusName){

        return AppUserStatus.FINDER.query().where().eq("appUserStatusName", appUserStatusName).findOne();
    }
}
