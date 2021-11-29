import business.user.UserBL;
import client.UserClient;
import io.restassured.response.Response;
import model.user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.UserRepository;

public class UserTest {

    private User user;
    private UserBL userBL;

    @BeforeTest
    public void setUp() {
        user = new User();
        userBL = new UserBL();
    }

    @Test
    public void userCreationUpdateAndDeleteTest() {
        /**Set up */
        user = UserRepository.getValidUser();

        /**Create test*/
        userBL.createNewUser(user);
        User userFromResponse = userBL.getUser(user.getUsername());
        Assert.assertEquals(user.getId(), userFromResponse.getId(), "Error - user id from response is invalid");

        /** update test */
        String usernameOfUserToUpdate = user.getUsername();
        user.setUsername(RandomStringUtils.randomAlphabetic(7));
        userBL.updateUser(usernameOfUserToUpdate, user);

        User updatedUser = userBL.getUser(user.getUsername());
        Assert.assertEquals(user.getId(), updatedUser.getId(), "Error - user id from response is invalid");

        /**delete updated user */
        userBL.deleteUser(updatedUser.getUsername(),user);
    }

    @Test
    public void loginLogoutUserTest(){
        user = UserRepository.getValidUser();
        /** set up */
        userBL.createNewUser(user);
        userBL.getLogin(user);
        userBL.getLogout();
        userBL.deleteUser(user.getUsername(),user);
    }

    @Test
    public void updateUserTest(){
        user = UserRepository.getValidUser();
        /** set up */
        userBL.createNewUser(user);
        /** update test */
        String usernameOfUserToUpdate = user.getUsername();
        user.setUsername(RandomStringUtils.randomAlphabetic(7));
        userBL.updateUser(usernameOfUserToUpdate, user);
        User updatedUser = userBL.getUser(user.getUsername());
        Assert.assertEquals(user.getId(), updatedUser.getId(), "Error - user id from response is invalid");
        /**delete updated user */
        userBL.deleteUser(updatedUser.getUsername(),user);
    }

    @Test
    public void deleteUserTest(){
        user = UserRepository.getValidUser();
        userBL.createNewUser(user);
        userBL.deleteUser(user.getUsername(),user);
    }
    @Test
    public void createUsersFromListTest(){
        userBL.createUsersFromList(3);

    }
    @Test
    public void createUsersFromArrayTest(){
     userBL.createUsersFromArray(3);
    }

}
