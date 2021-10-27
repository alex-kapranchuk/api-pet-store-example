import business.user.UserBL;
import client.UserClient;
import io.restassured.response.Response;
import model.user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Test;
import repository.UserRepository;

public class UserTest {

    @Test
    public void testUserCreation() {
        User user = UserRepository.getValidUser();
        UserBL userBL = new UserBL();
        userBL.createNewUser(user);
        User userFromResponse = userBL.getUser(user.getUsername());
        Assert.assertEquals(user.getId(), userFromResponse.getId(), "Error - user id from response is invalid");

        String usernameOfUserToUpdate = user.getUsername();
        user.setUsername(RandomStringUtils.randomAlphabetic(7));
        userBL.updateUser(usernameOfUserToUpdate, user);

        User updatedUser = userBL.getUser(user.getUsername());
        Assert.assertEquals(user.getId(), updatedUser.getId(), "Error - user id from response is invalid");

        userBL.deleteUser(updatedUser.getUsername());
        Response response = new UserClient().getUser(updatedUser.getUsername());
        Assert.assertEquals(response.getStatusCode(), 404, "Error - user has not been deleted");
    }
}
