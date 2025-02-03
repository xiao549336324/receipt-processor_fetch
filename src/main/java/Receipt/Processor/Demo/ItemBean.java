package Receipt.Processor.Demo;

import lombok.Data;

/**
 * ItemBean represents an individual item in a receipt.
 * It contains a short description and the item's price.
 *
 * Lombok's @Data annotation automatically generates:
 * - Getters and Setters
 * - toString() method
 * - equals() and hashCode() methods
 */
@Data
public class ItemBean {
    private String shortDescription;
    private String price;
}
