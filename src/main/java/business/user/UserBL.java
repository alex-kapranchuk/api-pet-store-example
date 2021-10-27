package business.user;

import client.UserClient;
import io.restassured.response.Response;
import model.user.User;
import org.testng.Assert;

public class UserBL {

    private UserClient userClient;

    public UserBL() {
        this.userClient = new UserClient();
    }

    public void createNewUser(User user) {
        Response response = userClient.createUser(user);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }

    public void updateUser(String username, User user) {
        Response response = userClient.updateUser(username, user);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }

    public User getUser(String username) {
        Response response = userClient.getUser(username);
       int countOfIterations = 0;
       while (response.getStatusCode() != 200 && countOfIterations < 10) {
           try {
               Thread.sleep(1000);
               response = userClient.getUser(username);
               countOfIterations++;
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        return response.as(User.class);
    }

    public void deleteUser(String username) {
        Response response = userClient.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }
 }
