package saga.order;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private Map<String, Boolean> orderStore = new HashMap<>();
    private boolean simulateFailure = false;

    @PostMapping("/create")
    public ResponseEntity<String> createOrder() {
        try {
            if (simulateFailure) {
                throw new RuntimeException("Simulated failure");
            }
            // Simulate order creation
            orderStore.put("order123", true);
            return ResponseEntity.ok("Order created");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Order creation failed");
        }
    }

    @PostMapping("/compensate")
    public ResponseEntity<String> compensateOrder() {
        try {
            if (simulateFailure) {
                throw new RuntimeException("Simulated failure");
            }
            // Simulate order compensation
            orderStore.remove("order123");
            return ResponseEntity.ok("Order compensation successful");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Order compensation failed");
        }
    }

    @PostMapping("/simulateFailure")
    public void simulateFailure(@RequestParam boolean simulateFailure) {
        this.simulateFailure = simulateFailure;
    }
}