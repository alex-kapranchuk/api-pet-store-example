import business.UserBL;
import model.UserModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.UserRepository;

public class UserTest {
    private UserModel userModel;
    private UserBL userBL;

    @BeforeTest
    public void setUp() {
        userModel = new UserModel();
        userBL = new UserBL();
        userModel = UserRepository.getValidUser();
    }

    @Test
    public void userCreationUpdateAndDeleteTest() {
        userBL.createNewUser(userModel);
    }

    @Test
    public void loginLogoutUserTest(){
        userBL.createNewUser(userModel);
        userBL.getLogin(userModel);
        userBL.getLogout();
        userBL.deleteUser(userModel.getUsername(), userModel);
    }

    @Test
    public void updateUserTest(){
        userBL.createNewUser(userModel);
        /** update test */
        String usernameOfUserToUpdate = userModel.getUsername();
        userModel.setUsername(RandomStringUtils.randomAlphabetic(7));
        userBL.updateUser(usernameOfUserToUpdate, userModel);
        UserModel updatedUserModel = userBL.getUser(userModel.getUsername());
        Assert.assertEquals(userModel.getId(), updatedUserModel.getId(), "Error - user id from response is invalid");
        /**delete updated user */
        userBL.deleteUser(updatedUserModel.getUsername(), userModel);
    }

    @Test
    public void deleteUserTest(){
        userBL.createNewUser(userModel);
        userBL.deleteUser(userModel.getUsername(), userModel);
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
