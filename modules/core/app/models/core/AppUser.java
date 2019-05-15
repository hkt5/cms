package models.core;

import io.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AppUser extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.MaxLength(255)
    public String appUserName;

    @Column(nullable = false, unique = true)
    @Constraints.Required
    @Constraints.Email
    @Constraints.MaxLength(255)
    public String appUserEmail;

    @Constraints.Required
    @Constraints.MaxLength(30)
    public String appUserPhone;

    @Constraints.Required
    @Constraints.MaxLength(255)
    public String appUserLoginPassword;

    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date appUserCreatedAt;

    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date appUserUpdatedAt;

    @ManyToOne
    @Constraints.Required
    public AppUserStatus appUserStatus;

    @ManyToOne
    @Constraints.Required
    public AppUserRole appUserRole;

    @OneToOne(mappedBy = "appUser")
    public AppUserPassword appUserPassword;
}
