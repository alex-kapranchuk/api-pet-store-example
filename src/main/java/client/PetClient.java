package client;

import io.restassured.response.Response;
import model.user.Pet;

import static io.restassured.RestAssured.given;

public class PetClient extends HttpClient{

    public PetClient(){super("pet");}

    public Response createPetToStory(Pet pet) {
        return given(defaultRequestSpecification)
                .body(pet)
                .post();
    }
    /** Add get name from Pet */
}
