package com.like.minded.backend.domain.user;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class UserRoleTest {

    @Test
    void testUserRoleCreationAndProperties() {
        Integer roleType = 1;
        String roleDesc = "ADMIN";

        UserRole userRole = new UserRole(roleType, roleDesc);

        assertNotNull(userRole, "User role object should not be null");
        assertEquals(roleType, userRole.getRoleType(), "Role type should match the provided value");
        assertEquals(roleDesc, userRole.getRoleDesc(), "Role description should match the provided value");
    }
}
