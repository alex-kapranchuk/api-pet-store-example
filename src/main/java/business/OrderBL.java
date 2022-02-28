package business;

import client.OrderClient;
import client.PetClient;
import client.UserClient;
import io.restassured.response.Response;
import model.OrderModel;
import model.PetModel;
import org.jetbrains.annotations.NotNull;
import org.testng.Assert;


public class OrderBL {

    private OrderClient orderClient;


    public OrderBL() {
        this.orderClient = new OrderClient();
    }

    // 404 error but data is present
    public void getOrderById(@NotNull OrderModel orderModel){
        Response response = orderClient.getOrderId(orderModel.getPetId());
        System.out.println(orderModel.getPetId());
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }
    public void getOrdersByStatus(OrderModel orderModel){
        Response response = orderClient.getOrdersStatus();
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
    }

// does not work
    public void postOrder(OrderModel orderModel){
        Response response = orderClient.postOrder(orderModel);
        Assert.assertEquals(response.getStatusCode(), 200, "Error - status code is not correct");
        Assert.assertEquals((boolean) response.getBody().as(OrderModel.class).getComplete(), true);
    }

    public void deleteOrder(OrderModel orderModel){
        Response response = orderClient.deleteOrder(orderModel.getId());
        Assert.assertEquals(response.getStatusCode() , 404 , "Error - status code is not correct");
    }



}
