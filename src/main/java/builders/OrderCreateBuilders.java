package builders;

import model.OrderModel;
import org.apache.commons.lang3.RandomStringUtils;
import utils.GetData;

public class OrderCreateBuilders {
    private int id = Integer.parseInt(RandomStringUtils.randomNumeric(6));
    private int quantity = Integer.parseInt(RandomStringUtils.randomNumeric(1));
    private String status = OrderModel.Status.PLACED.getValue();
    private OrderModel orderModel;

    public OrderModel createOrder(int petId){
        orderModel = OrderModel.builder()
                .id(id)
                .petId(petId)
                .quantity(quantity)
                .shipDate(GetData.getShipDate())
                .status(status)
                .complete(true)
                .build();
        return orderModel;
    }
}
