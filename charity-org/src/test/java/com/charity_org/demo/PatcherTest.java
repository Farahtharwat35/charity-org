package com.charity_org.demo;

import com.charity_org.demo.Models.User;
import com.charity_org.demo.Patcher.Patcher;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class PatcherTest {

    @Test
    void userPatcher_updatesOnlyNonNullFields() throws IllegalAccessException {
        // Creating existing and incomplete User objects
        User existingUser = new User();
        existingUser.setVisitDate(new Date(1630454400000L)); // Sample date
        existingUser.setNumberOfActionsTaken(5);

        User incompleteUser = new User();
        incompleteUser.setVisitDate(null); // visitDate should remain the same
        incompleteUser.setNumberOfActionsTaken(10); // numberOfActionsTaken should update

        // Using the Patcher to update existingUser with incompleteUser's values
        Patcher.userPatcher(existingUser, incompleteUser);

        // Assert: Verifying fields in existingUser are updated as expected
        assertEquals(new Date(1630454400000L), existingUser.getVisitDate()); // visitDate should remain the same
        assertEquals(10, existingUser.getNumberOfActionsTaken()); // numberOfActionsTaken should be updated
    }
}
