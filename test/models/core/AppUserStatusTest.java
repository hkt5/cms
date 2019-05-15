package models.core;

import org.junit.Test;
import play.test.WithApplication;

import static org.junit.Assert.*;

public class AppUserStatusTest extends WithApplication {

    private final int expectedSize = 2;

    private final Long activeStatusId = 1L;
    private final Long inactiveStatusId =2L;
    private final Long notExistsStatusId = 3L;

    private final String activeStatusName = "active";
    private final String inactiveStatusName = "inactive";
    private final String notExistsStatusName = "not exists";

    @Test
    public void appStatusSizeTest(){

        assertEquals(this.expectedSize, AppUserStatus.findAll().size());
    }

    @Test
    public void activeStatusIdTest(){

        assertNotNull(AppUserStatus.findById(this.activeStatusId));
    }

    @Test
    public void inactiveStatusIdTest(){

        assertNotNull(AppUserStatus.findById(this.inactiveStatusId));
    }

    @Test
    public void notExistsStatusIdTest(){

        assertNull(AppUserStatus.findById(this.notExistsStatusId));
    }

    @Test
    public void activeStatusNameTest(){

        assertNotNull(AppUserStatus.findByName(this.activeStatusName));
    }

    @Test
    public void inactiveStatusNameTest(){

        assertNotNull(AppUserStatus.findByName(this.inactiveStatusName));
    }

    @Test
    public void notExistsStatusNameTest(){

        assertNull(AppUserStatus.findByName(this.notExistsStatusName));
    }
}