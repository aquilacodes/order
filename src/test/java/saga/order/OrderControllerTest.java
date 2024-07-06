package saga.order;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OrderControllerTest {

    private final OrderController orderController = new OrderController();

    @Test
    public void testCreateOrder_Success() {
        orderController.simulateFailure(false);
        ResponseEntity<String> response = orderController.createOrder();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order created", response.getBody());
    }

    @Test
    public void testCreateOrder_Failure() {
        orderController.simulateFailure(true);
        ResponseEntity<String> response = orderController.createOrder();
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Order creation failed", response.getBody());
    }

    @Test
    public void testCompensateOrder_Success() {
        orderController.simulateFailure(false);
        ResponseEntity<String> response = orderController.compensateOrder();
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Order compensation successful", response.getBody());
    }

    @Test
    public void testCompensateOrder_Failure() {
        orderController.simulateFailure(true);
        ResponseEntity<String> response = orderController.compensateOrder();
        assertEquals(500, response.getStatusCodeValue());
        assertEquals("Order compensation failed", response.getBody());
    }
}