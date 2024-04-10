/* LikeMinded (C)2024 */
package com.like.minded.backend.domain.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class UserRoleTest {

    @Test
    void testUserRoleCreationAndProperties() {
        Integer roleType = 1;
        String roleDesc = "ADMIN";

        UserRole userRole = new UserRole(roleType, roleDesc);

        assertNotNull(userRole, "User role object should not be null");
        assertEquals(roleType, userRole.getRoleType(), "Role type should match the provided value");
        assertEquals(
                roleDesc,
                userRole.getRoleDesc(),
                "Role description should match the provided value");
    }

    @Test
    void testEqualsWithSameValues() {
        UserRole role1 = new UserRole(1, "Admin");
        UserRole role2 = new UserRole(1, "Admin");

        assertEquals(role1, role2, "Roles with the same values should be considered equal.");
        assertEquals(
                role1,
                role2,
                "Roles with the same values should be considered equal using equals().");
    }

    @Test
    void testEqualsWithDifferentValues() {
        UserRole role1 = new UserRole(1, "Admin");
        UserRole role2 = new UserRole(2, "User");

        assertNotEquals(
                role1, role2, "Roles with different values should not be considered equal.");
        assertNotEquals(
                role1,
                role2,
                "Roles with different values should not be considered equal using equals().");
    }

    @Test
    void testHashCodeConsistencyAndEquality() {
        UserRole role1 = new UserRole(1, "Admin");
        UserRole role2 = new UserRole(1, "Admin");
        UserRole role3 = new UserRole(2, "User");

        assertEquals(
                role1.hashCode(), role2.hashCode(), "Equal objects must have the same hash code.");
        assertNotEquals(
                role1.hashCode(),
                role3.hashCode(),
                "Ideally, unequal objects have different hash codes.");

        int initialHashCode = role1.hashCode();
        assertEquals(
                initialHashCode,
                role1.hashCode(),
                "Hash code should remain consistent across invocations.");
    }
}
