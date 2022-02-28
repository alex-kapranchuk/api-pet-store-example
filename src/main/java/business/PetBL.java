package business;

import client.PetClient;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.response.Response;

import model.PetModel;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.Collections;

import static io.restassured.RestAssured.given;


public class PetBL {

    private PetClient petClient;

    public PetBL() {
        this.petClient = new PetClient();
    }



    public void addNewPetToStory(PetModel petModel) {
        Response response = petClient.createPetToStory(petModel);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }
    /** get id */
    /*public void allPets(PetModel petModel){
        Response response = petClient.getPet(petModel.getId());
        ArrayList<Integer> arrayListOfId = new ArrayList<>();
        Collections.addAll(arrayListOfId,response.getBody().as(PetModel.class).getId());
        arrayListOfId.forEach(System.out::println);
    }*/

    public PetModel getPet(int petID) {
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
        return response.as(PetModel.class);
    }

    /**
     * to update
     */
    public PetModel updateDataOfPet(PetModel petModel) {
        petModel.setName(RandomStringUtils.randomAlphabetic(7));
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
        return petModel;
    }

    public void updatePet(PetModel petModel) {
        Response response = petClient.updatePet(petModel);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }

    public void deletePet(int id, PetModel petModel) {
        Response response = petClient.deletePet(id);

        Assert.assertEquals(response.getStatusCode(), 404, "Error - status code is not correct");
//      Assert.assertNull(response.jsonPath(JsonPathConfig.jsonPathConfig()));
    }

    public void getPetByStatus(String PetStatus){
        Response response = petClient.findPetByStatus(PetStatus);
        Assert.assertEquals(response.getStatusCode(),200,"Error - status code is not correct");
        Assert.assertTrue(response.getBody().equals(PetModel.Status.PENDING),"Response body is not contains correct status");
    }
    public void uploadImageTest(PetModel petModel){
        Response response = petClient.uploadImage(petModel.getId());
        Assert.assertEquals(response.getStatusCode(),200,"Error - status code is not correct");
    }
}