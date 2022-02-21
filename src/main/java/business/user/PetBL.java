package business.user;

import client.PetClient;
import io.restassured.response.Response;

import model.user.Pet;
import model.user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import static io.restassured.RestAssured.given;


public class PetBL {

    private PetClient petClient;

    public PetBL() {
        this.petClient = new PetClient();
    }

    public void addNewPetToStory(Pet pet) {
        Response response = petClient.createPetToStory(pet);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }

    public Pet getPet(int petID) {
        Response response = petClient.getPet(petID);
        /** wait response */
//        int countOfIterations = 0;
//        while (response.getStatusCode() != 200 && countOfIterations < 10) {
//            try {
//                Thread.sleep(1000);
//                response = petClient.getPet(petID);
//                countOfIterations++;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        return response.as(Pet.class);
    }

    /**
     * to update
     */
    public Pet updateDataOfPet(Pet pet) {
        pet.setName(RandomStringUtils.randomAlphabetic(7));
        /** wait response */
//        Response response = petClient.getPet(pet.getId());
//        int countOfIterations = 0;
//        while (response.getStatusCode() != 200 && countOfIterations < 10) {
//            try {
//                Thread.sleep(1000);
//                response = petClient.getPet(pet.getId());
//                countOfIterations++;
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
//        pet.setName("updatedPetTestName");
        return pet;

    }

    public void updatePet(Pet pet) {
        Response response = petClient.updatePet(pet);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }

    public void deletePet(int id, Pet pet) {
        Response response = petClient.deletePet(id);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }

}