package client;

import io.restassured.response.Response;
import model.PetModel;

import java.io.File;

import static io.restassured.RestAssured.given;

public class PetClient extends HttpClient{

    public PetClient(){
        super("pet");}

    public Response createPetToStory(PetModel petModel) {
        return given(defaultRequestSpecification)
                .body(petModel)
                .post();
    }
    public Response getPet(int id) {
        return given(defaultRequestSpecification)
                .get("/{petId}", id);
    }
    public Response updatePet(PetModel petModel){
        return given(defaultRequestSpecification)
                .body(petModel)
                .put();
    }
    public Response deletePet(int id){
        return given(defaultRequestSpecification)
                .delete("/{petId}", id);
    }
    public Response findPetByStatus(String value){
        return given(defaultRequestSpecification)
                .get("findByStatus?status=" + value);
    }
    public Response uploadImage(int id){
        return given(defaultRequestSpecification)
                .pathParams("id", id )
                .contentType("multipart/from-data")
                .multiPart(new File("/Users/okapranchuk/Desktop/pic.png"))
                .post("/{id}/uploadImage");
    }
}
