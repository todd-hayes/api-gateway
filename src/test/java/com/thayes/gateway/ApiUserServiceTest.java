package com.thayes.gateway;

import com.thayes.gateway.entity.ApiUser;
import com.thayes.gateway.entity.UserRole;
import com.thayes.gateway.exception.UserNotFoundException;
import com.thayes.gateway.service.UserService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ApiUserServiceTest extends BaseUserTest {

    @Autowired
    private UserService userService;

    private List<ApiUser> testUsers = new ArrayList<>();

    @BeforeAll
    public void createTestUser() {
        ApiUser newUser = createRandomTestUser();
        ApiUser user = userService.saveUser(newUser);
        testUsers.add(user);
    }

    @Test
    public void getAllUsersTest_Success() {
        List<ApiUser> allApiUsers = userService.getAllUsers();
        assertFalse(allApiUsers.isEmpty());
    }

    @Test
    public void createUserTest() {
        ApiUser newUser = createRandomTestUser();
        ApiUser user = userService.saveUser(newUser);
        assertNotNull(user);
        testUsers.add(user);
    }

    @Test
    public void getUserTest_Success() {
        ApiUser newUser = createRandomTestUser();
        ApiUser user = userService.saveUser(newUser);
        testUsers.add(user);
        assertNotNull(user);
    }

    @Test
    public void getSpecificUserTest() {
        ApiUser user = userService.getUser("admin");
        assertEquals(2, user.getUserRoles().size());
        Set<UserRole> roleSet = new HashSet<>(user.getUserRoles());
        assertEquals(user.getUserRoles().size(), roleSet.size());
    }

    @Test
    public void getUserByUserNameTest_Success() {
        String userName = testUsers.get(0).getUserName();
        ApiUser thayesUser = userService.getUser(userName);
        assertNotNull(thayesUser);
    }

    @Test
    public void updateUserTest_Success() {
        String userName = testUsers.get(0).getUserName();
        ApiUser updateUser = userService.getUser(userName);
        updateUser.setActive(false);
        ApiUser updatedUser = userService.saveUser(updateUser);
        assertFalse(updatedUser.isActive());
        updatedUser.setActive(true);
        updatedUser = userService.saveUser(updatedUser);
        assertTrue(updatedUser.isActive());
    }

    @Test
    public void deleteApiUserTest() {
        ApiUser deleteUser = createRandomTestUser();
        userService.saveUser(deleteUser);
        String deleteUserName = deleteUser.getUserName();
        userService.deleteUser(deleteUserName);
        try {
            ApiUser noUser = userService.getUser(deleteUserName);
        } catch (UserNotFoundException exc) {
            assertTrue(true);
        }
    }

    @Disabled("Do not want to delete all the records from the table")
    @Test
    public void deleteAllApiUsersTest() {
        userService.deleteAllUsers();
        List<ApiUser> allUsers = userService.getAllUsers();
        assertEquals(0, allUsers.size());
    }

    @AfterAll
    public void cleanUpTestData() {
         // Find the test users created for testing
         List<ApiUser> usersToDelete = userService.getAllUsers().stream()
                 .filter(a -> a.getUserName().startsWith("testuser"))
                 .toList();
         // Delete test users from the database
        for (ApiUser user : usersToDelete) {
            userService.deleteUser(user.getUserName());
        }
    }
}
