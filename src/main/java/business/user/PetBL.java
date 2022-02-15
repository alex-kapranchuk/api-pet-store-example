package business.user;

import client.PetClient;
import io.restassured.response.Response;

import model.user.Pet;
import model.user.User;
import org.testng.Assert;



public class PetBL {

    private PetClient petClient;
    public PetBL() {
        this.petClient = new PetClient();
    }

    public void addNewPetToStory(Pet pet) {
        Response response = petClient.createPetToStory(pet);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }

  /*  public Pet getPet(String petName) {
        Response response = petClient.getPet(petName);
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
    }*/
}
