import builders.PetCreateBuilders;
import business.PetBL;
import model.PetModel;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Ignore;
import org.testng.annotations.Test;

public class PetTest {
    private PetModel petModel;
    private PetBL petBL;

    @BeforeClass
    public void setUp() {
        petModel = new PetCreateBuilders().createPet();
        petBL = new PetBL();
    }

    @Test
    public void addNewPetToStoryTest(){
        /**Create test*/
        petBL.addNewPetToStory(petModel);

    }
    @Test
    public void findPetById(){
        /** Find pet by id test */
        petBL.addNewPetToStory(petModel);
        PetModel petModelFromResponse = petBL.getPet(petModel.getId()); // crate get name from pet
        Assert.assertEquals(petModel.getName(), petModelFromResponse.getName(), "Error - pet name from response is not found");
    }
    @Test
    public void updatePet() throws InterruptedException {
        /** Update an existing pet  */
        petBL.addNewPetToStory(petModel);
        petBL.updateDataOfPet(petModel);
        PetModel updatedPetModel = petBL.getPet(petModel.getId());
        Assert.assertEquals(petModel.getId(), updatedPetModel.getId(), "Error - user id is different");
        Assert.assertNotEquals(petModel.getName(), updatedPetModel.getName(), "Error - names are not different");
        petBL.deletePet(petModel.getId(), petModel);
    }
    @Test
    public void deletePet(){
        petBL.addNewPetToStory(petModel);
        petBL.deletePet(petModel.getId(), petModel);

    }
    @Test
    public void findPetByStatus(){
     petBL.getPetByStatus("pending");
    }

    @Test
    public void uploadImagePet(){
        petBL.addNewPetToStory(petModel);
        petBL.uploadImageTest(petModel);
    }
}
