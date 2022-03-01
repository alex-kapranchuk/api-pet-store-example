package client;

import io.restassured.response.Response;
import model.OrderModel;

import static io.restassured.RestAssured.given;

public class OrderClient extends HttpClient{

    public OrderClient() {
        super("store");
    }

    public Response getOrderId(int id){
        return given(defaultRequestSpecification)
                .accept("application/json")
                .get("/order/" + id);
    }
    public Response getOrdersStatus(){
        return given(defaultRequestSpecification)
                .get("/inventory");
    }
    public Response postOrder(OrderModel orderModel){
        return given(defaultRequestSpecification)
                .body(orderModel)
                .accept("application/json")
                .contentType("application/json")
                .post("/order");
    }
    public Response deleteOrder(int id){
        return given(defaultRequestSpecification)
                .delete("/order/{id}", id);
    }
}
