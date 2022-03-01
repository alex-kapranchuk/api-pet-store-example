import builders.OrderCreateBuilders;
import builders.PetCreateBuilders;
import business.OrderBL;
import model.OrderModel;
import model.PetModel;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class OrderTest {
    private OrderModel orderModel;
    private OrderBL orderBL;
    private PetModel petModel;

    @BeforeClass
    public void setUp() {
        orderModel = new OrderModel();
        orderBL = new OrderBL();
        petModel = new PetCreateBuilders().createPet();
        orderModel = new OrderCreateBuilders().createOrder(petModel.getId());
    }

    @Test()
    public void findOrderTest() {
        orderBL.getOrdersByStatus(orderModel);
    }

    @Test // swagger error required digits from 1 to 9
    public void findOrderById(){
        orderBL.getOrderById(orderModel);
    }

    @Test
    public void postOrder() {
        orderBL.postOrder(orderModel);
    }

    @Test
    public void deleteOrder() {
        orderBL.deleteOrder(orderModel);
    }
}
