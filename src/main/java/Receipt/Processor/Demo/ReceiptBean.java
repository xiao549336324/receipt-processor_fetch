package Receipt.Processor.Demo;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * ReceiptBean represents a receipt with details about the retailer,
 * purchase date and time, items bought, and the total amount.
 *
 * Lombok's @Data annotation automatically generates:
 * - Getters and Setters
 * - toString() method
 * - equals() and hashCode() methods
 */
@Data
public class ReceiptBean {
    private String retailer;
    private LocalDate purchaseDate;
    private LocalTime purchaseTime;
    private List<ItemBean> items;
    private String total;
}