package business;

import builders.UserCreateBuilders;
import client.UserClient;
import io.restassured.response.Response;
import model.UserModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.codehaus.groovy.runtime.DefaultGroovyMethods.collect;

public class UserBL {

    private UserClient userClient;
    private List<UserModel> userModelList;
    private UserModel[] userModelArray;

    public UserBL() {
        this.userClient = new UserClient();
    }

    public void createNewUser(UserModel userModel) {
        Response response = userClient.createUser(userModel);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");

    }
    private List<UserModel> createListOfUsers(int countOfUsers) {
        return  IntStream.range(0, countOfUsers)
                .mapToObj(el -> new UserCreateBuilders().createUser())
                .collect(Collectors.toList());
    }

    public void createUsersFromList(int countOfUsers){
        userModelList = createListOfUsers(countOfUsers);
        for (UserModel testModel : userModelList) {
            Response response = userClient.createUser(testModel);
            Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        }
    }
    private UserModel[] createArrayOfUsers(int countOfUsers){
        UserModel[] result = new UserModel[countOfUsers];
        for (int i = 0; i <countOfUsers ; i++) {
            result[i] = new UserCreateBuilders().createUser();
        }
        return result;
    }
    public void createUsersFromArray(int countOfUsers){
        userModelArray = createArrayOfUsers(countOfUsers);
        for (int i = 0; i < countOfUsers; i++) {
            Response response = userClient.createUser(userModelArray[i]);
            Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        }
    }


/**to update */
    private UserModel updateDataOfUser(UserModel userModel){
        userModel.setUsername(RandomStringUtils.randomAlphabetic(7));
        return userModel;
    }

    public void updateUser(String username, UserModel userModel) {
        Response response = userClient.updateUser(username, userModel);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }

    public UserModel getUser(String username) {
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
        return response.as(UserModel.class);
    }

    public void deleteUser(String username, UserModel userModel) {
        Response response = userClient.deleteUser(username);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");

// negative test
        userClient.getUser(userModel.getUsername());
        Response response2 = new UserClient().getUser(userModel.getUsername());
        int countOfIterations = 0;
        while (response.getStatusCode() != 200 && countOfIterations < 10) {
            try {
                Thread.sleep(1000);
                response = new UserClient().getUser(userModel.getUsername());
                countOfIterations++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        Assert.assertEquals(response2.getStatusCode(), 404, "Error - user has not been deleted");
    }

    public void getLogin(UserModel userModel){
        Response response = userClient.loginUser(userModel.getUsername(), userModel.getPassword());
        Assert.assertEquals(response.getStatusCode(), 200, "Error status code is not correct ");
    }

    public void getLogout(){
        Response response = userClient.LogoutUser();
        Assert.assertEquals(response.getStatusCode(), 200, "Error status code is not correct ");
    }



}
