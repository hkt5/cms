package models.core;

import io.ebean.Model;
import play.data.format.Formats;
import play.data.validation.Constraints;

import javax.persistence.*;
import java.util.Date;

@Entity
public class AppUserPassword extends Model {

    @Id
    @GeneratedValue
    public Long id;

    @OneToOne
    @Column(nullable = false, unique = true)
    @Constraints.Required
    public AppUser appUser;

    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date appUserPasswordCreatedAt;

    @Constraints.Required
    @Formats.DateTime(pattern = "yyyy/mm/dd")
    public Date appUserPasswordUpdatedAt;
}