package client;

import io.restassured.response.Response;
import model.user.Pet;

import static io.restassured.RestAssured.given;

public class PetClient extends HttpClient{

    public PetClient(){
        super("pet");}

    public Response createPetToStory(Pet pet) {
        return given(defaultRequestSpecification)
                .body(pet)
                .post();
    }
    public Response getPet(int id) {
        return given(defaultRequestSpecification)
                .get("/{petId}", id);
    }
    public Response updatePet(Pet pet){
        return given(defaultRequestSpecification)
                .body(pet)
                .put();
    }
    public Response deletePet(int id){
        return given(defaultRequestSpecification)
                .delete("/{petId}", id);
    }
    public Response findPetByStatus(String value){
        return given(defaultRequestSpecification)
                .get("/findByStatus",value);
    }
}
