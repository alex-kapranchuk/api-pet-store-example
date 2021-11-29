package business.user;

import builders.UserCreateBuilders;
import client.UserClient;
import io.restassured.response.Response;
import model.user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

public class UserBL {

    private UserClient userClient;
    private List<User> userList;
    private User [] userArray;

    public UserBL() {
        this.userClient = new UserClient();
    }

    public void createNewUser(User user) {
        Response response = userClient.createUser(user);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }
    private List<User> createListOfUsers(int countOfUsers) {
        return  IntStream.range(0, countOfUsers)
                .mapToObj(el -> new UserCreateBuilders().createUser())
                .collect(Collectors.toList());
    }

    public void createUsersFromList(int countOfUsers){
        userList = createListOfUsers(countOfUsers);
        for (User testModel : userList) {
            Response response = userClient.createUser(testModel);
            Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        }
    }
    private User [] createArrayOfUsers(int countOfUsers){
        User[] result = new User[countOfUsers];
        for (int i = 0; i <countOfUsers ; i++) {
            result[i] = new UserCreateBuilders().createUser();
        }
        return result;
    }
    public void createUsersFromArray(int countOfUsers){
        userArray = createArrayOfUsers(countOfUsers);
        for (int i = 0; i < countOfUsers; i++) {
            Response response = userClient.createUser(userArray[i]);
            Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        }
    }


/**to update */
    private User updateDataOfUser(User user){
        user.setUsername(RandomStringUtils.randomAlphabetic(7));
        return user;
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

    public void deleteUser(String username, User user) {
        Response response = userClient.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");

// negative test
        userClient.getUser(user.getUsername());
        Response response2 = new UserClient().getUser(user.getUsername());
        int countOfIterations = 0;
        while (response.getStatusCode() != 200 && countOfIterations < 10) {
            try {
                Thread.sleep(1000);
                response = new UserClient().getUser(user.getUsername());
                countOfIterations++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(response2.getStatusCode(), 404, "Error - user has not been deleted");
    }

    public void getLogin(User user){
        Response response = userClient.loginUser(user.getUsername(),user.getPassword());
        Assert.assertEquals(response.getStatusCode(), 200, "Error status code is not correct ");
    }

    public void getLogout(){
        Response response = userClient.LogoutUser();
        Assert.assertEquals(response.getStatusCode(), 200, "Error status code is not correct ");
    }



}
