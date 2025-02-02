package Receipt.Processor.Demo;

import lombok.Data;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class ReceiptBean {
    private String retailer;
    private LocalDate purchaseDate;
    private LocalTime purchaseTime;
    private List<ItemBean> items;
    private String total;
}