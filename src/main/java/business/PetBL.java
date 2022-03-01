package business;

import client.PetClient;
import io.restassured.response.Response;
import model.PetModel;
import model.ResponseAPI;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;

import static org.assertj.core.api.Assertions.assertThat;


public class PetBL {

    private final PetClient petClient;
    private final PetModel petModel;

    public PetBL() {
        this.petClient = new PetClient();
        this.petModel = new PetModel();
    }

    public void addNewPetToStory(PetModel petModel) {
        Response response = petClient.createPetToStory(petModel);
        waitResponsePet(response, petModel);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        assertThat(response.getBody().as(PetModel.class).getName().equals(petModel.getName()));
    }

    public PetModel getPet(int petID) {
        Response response = petClient.getPet(petID);
        /** wait response */
        waitResponsePet(response, petModel);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        assertThat(response.getBody().as(PetModel.class).getName().equals(petModel.getName()));
        return response.as(PetModel.class);
    }

    /**
     * wait response
     */
    public void waitResponsePet(Response response, PetModel petModel) {
        int i = 0;
        while (response.getStatusCode() != 200 && i < 10) {
            try {
                Thread.sleep(1000);
                response = petClient.findPetByStatus(petModel.getStatus());
                i++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * to update
     */
    public PetModel updateDataOfPet(PetModel petModel) {
        Response response = petClient.getPet(petModel.getId());
        waitResponsePet(response, petModel);
        petModel.setName(RandomStringUtils.randomAlphabetic(7));
//      petModel.setName("updatedPetTestName");
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        return petModel;
    }


    public void deletePet(int id, PetModel petModel) {
        Response response = petClient.deletePet(id);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        assertThat(response.getBody().as(ResponseAPI.class).getMessage().equals(String.valueOf(petModel.getId())));
    }

    public void getPetByStatus(String PetStatus) {
        Response response = petClient.findPetByStatus(PetStatus);
        waitResponsePet(response, petModel);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        Assert.assertTrue(response.getBody().equals(PetModel.Status.PENDING), "Response body is not contains correct status");
    }

    public void uploadImageTest(PetModel petModel) {
        Response response = petClient.uploadImage(petModel.getId());
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }
    /**
     * get id
     */
    /*public void allPets(PetModel petModel){
        Response response = petClient.getPet(petModel.getId());
        ArrayList<Integer> arrayListOfId = new ArrayList<>();
        Collections.addAll(arrayListOfId,response.getBody().as(PetModel.class).getId());
        arrayListOfId.forEach(System.out::println);
    }*/
}