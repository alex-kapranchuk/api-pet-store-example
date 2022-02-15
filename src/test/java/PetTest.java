import business.user.PetBL;
import io.restassured.response.Response;
import model.user.Pet;
import model.user.User;
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
    }
    @Test
    public void addNewPetToStoryTest(){
        pet = PetRepository.getValidPet();

        /**Create test*/
        petBL.addNewPetToStory(pet);
        /*Pet petFromResponse = petBL.addNewPetToStory(); // crate get name from pet
        Assert.assertEquals(pet.getId(), petFromResponse.getId(), "Error - user id from response is invalid");*/
    }
}
