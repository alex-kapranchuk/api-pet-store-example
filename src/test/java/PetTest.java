import business.user.PetBL;
import io.restassured.response.Response;
import model.user.Pet;
import model.user.User;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import repository.PetRepository;
import repository.UserRepository;


public class PetTest {
    private Pet pet;
    private PetBL petBL;

    @BeforeTest
    public void setUp() {
        pet = new Pet();
        petBL = new PetBL();
        pet = PetRepository.getValidPet();
    }
    @Test
    public void addNewPetToStoryTest(){
        /**Create test*/
        petBL.addNewPetToStory(pet);

    }
    @Test
    public void findPetById(){
        /** Find pet by id test */
        petBL.addNewPetToStory(pet);
        Pet petFromResponse = petBL.getPet(pet.getId()); // crate get name from pet
        Assert.assertEquals(pet.getName(), petFromResponse.getName(), "Error - pet name from response is not found");
    }
    @Test
    public void updatePet() throws InterruptedException {
        /** Update an existing pet  */
        petBL.addNewPetToStory(pet);
        petBL.updatePet(petBL.updateDataOfPet(pet));
        Pet updatedPet = petBL.getPet(pet.getId());
        Assert.assertEquals(pet.getId(), updatedPet.getId(), "Error - user id is different");
        Assert.assertNotEquals(pet.getName(),updatedPet.getName(), "Error - names are not different");
        petBL.deletePet(pet.getId(), pet);
        // add negative test where we get some pet from invalid deleted id
    }

}
