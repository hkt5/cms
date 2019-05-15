package models.core;

import org.junit.Test;
import play.test.WithApplication;

import static org.junit.Assert.*;

public class AppUserRoleTest extends WithApplication {

    private final int appUserRolesExpectedSize = 5;

    private final Long guestRoleId = 1L;
    private final Long userRoleId = 2L;
    private final Long customerRoleId = 3L;
    private final Long employeeRoleId = 4L;
    private final Long adminRoleId = 5L;
    private final Long notExistsRoleId = 6L;

    private final String guestRoleName = "guest";
    private final String userRoleName = "user";
    private final String customerRoleName = "customer";
    private final String employeeRoleName = "employee";
    private final String adminRoleName = "admin";
    private final String notEXistsRoleName = "not exists role name";

    @Test
    public void findAllRolesTest(){

        assertEquals(this.appUserRolesExpectedSize, AppUserRole.findAll().size());
    }

    @Test
    public void guestRoleIdTest(){

        assertNotNull(AppUserRole.findById(this.guestRoleId));
    }

    @Test
    public void userRoleIdTest(){

        assertNotNull(AppUserRole.findById(this.userRoleId));
    }

    @Test
    public void customerRoleIdTest(){

        assertNotNull(AppUserRole.findById(this.customerRoleId));
    }

    @Test
    public void employeeRoleIdTest(){

        assertNotNull(AppUserRole.findById(this.employeeRoleId));
    }

    @Test
    public void adminRoleIdTest(){

        assertNotNull(AppUserRole.findById(this.adminRoleId));
    }

    @Test
    public void notExistsRoleId(){

        assertNull(AppUserRole.findById(this.notExistsRoleId));
    }

    @Test
    public void guestRoleNameTest(){

        assertNotNull(AppUserRole.findByName(this.guestRoleName));
    }

    @Test
    public void userRoleNameTest(){

        assertNotNull(AppUserRole.findByName(this.userRoleName));
    }

    @Test
    public void customerRoleNameTest(){

        assertNotNull(AppUserRole.findByName(this.customerRoleName));
    }

    @Test
    public void employeeRoleNameTest(){

        assertNotNull(AppUserRole.findByName(this.employeeRoleName));
    }

    @Test
    public void adminRoleNameTest(){

        assertNotNull(AppUserRole.findByName(this.adminRoleName));
    }

    @Test
    public void notExistRoleNameTest(){

        assertNull(AppUserRole.findByName(this.notEXistsRoleName));
    }
}