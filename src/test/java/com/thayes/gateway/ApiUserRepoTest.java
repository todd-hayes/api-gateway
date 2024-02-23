package com.thayes.gateway;

import com.thayes.gateway.dao.ApiUserRepo;
import com.thayes.gateway.entity.ApiUser;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Optional;

@SpringBootTest
public class ApiUserRepoTest extends BaseUserTest {

    @Autowired
    ApiUserRepo apiUserRepo;

    @Test
    public void createApiUserTest() {
        ApiUser newUser = createRandomTestUser();
        apiUserRepo.save(newUser);
        assertNotNull(newUser.getUserName());
    }

    @Disabled @Test
    public void findAllApiUsersTest() {
        List<ApiUser> users = apiUserRepo.findAll();
        assertEquals(3, users.size());
    }

    @Test
    public void findByUserNameTest() {
        Optional<ApiUser> user = apiUserRepo.findById("thayes");
        assertTrue(user.isPresent());
    }

    @Test
    public void updateApiUserTest() {
        // find user
        String userName = "thayes";
        Optional<ApiUser> user = apiUserRepo.findById(userName);

        if (user.isPresent()) {
            ApiUser updateUser = user.get();
            updateUser.setActive(false);

            ApiUser updatedUser = apiUserRepo.save(updateUser);
            assertFalse(updatedUser.isActive());
        }
    }

    @Test
    public void deleteApiUserTest() {
        ApiUser deleteUser = createRandomTestUser();
        apiUserRepo.save(deleteUser);
        String deleteUserId = deleteUser.getUserName();
        apiUserRepo.delete(deleteUser);
        Optional<ApiUser> noUser = apiUserRepo.findById(deleteUserId);
        assertTrue(noUser.isEmpty());
    }

    @Disabled("Do not want to delete all the records from the table")
    @Test
    public void deleteAllApiUsersTest() {
        apiUserRepo.deleteAll();
        List<ApiUser> allUsers = apiUserRepo.findAll();
        assertEquals(0, allUsers.size());
    }

}
