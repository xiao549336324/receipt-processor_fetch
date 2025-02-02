package Receipt.Processor.Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.regex.Pattern;
import java.time.LocalDate;

@RestController
@RequestMapping("/receipts")
public class ReceiptProcessorController {

    @Autowired
	GlobalCache globalCache;

    @PostMapping("/process")
	public ResponseEntity<Map<String, String>> processReceipt(@RequestBody ReceiptBean receipt) {
		if (!isValidReceipt(receipt)) {
			return ResponseEntity.badRequest().body(Map.of("message", "The receipt is invalid. Please verify input."));
		}

		String receiptId = UUID.randomUUID().toString();
		globalCache.receiptBeanCacheMapPut(receiptId, receipt);

		int points = calculatePoints(receipt);
		globalCache.pointsCacheMapPut(receiptId, points);

		return ResponseEntity.ok(Map.of("id", receiptId));
	}

	@GetMapping("/{id}/points")
	public ResponseEntity<Map<String, String>> getPoints(@PathVariable String id) {
		Integer points = globalCache.pointsCacheMapGet(id);
		if (points == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("message", "No receipt found for that ID."));
		}
		return ResponseEntity.ok(Map.of("points", String.valueOf(points)));
	}

	private boolean isValidReceipt(ReceiptBean receipt) {

		return receipt.getRetailer() != null && receipt.getPurchaseDate() != null &&
				receipt.getPurchaseTime() != null && receipt.getItems() != null &&
				!receipt.getItems().isEmpty() && receipt.getTotal() != null &&
				Pattern.matches("^\\d+\\.\\d{2}$", receipt.getTotal());
	}

	private int calculatePoints(ReceiptBean receipt) {

		int points = 0;

		// 1. One point for every alphanumeric character in the retailer name.
		points += receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length();

		// 2. 50 points if the total is a round dollar amount with no cents.
		double total = Double.parseDouble(receipt.getTotal());
		if (total % 1.0 == 0) points += 50;

		// 3. 25 points if the total is a multiple of 0.25.
		if (total % 0.25 == 0) points += 25;

		// 4. 5 points for every two items on the receipt.
		points += (receipt.getItems().size() / 2) * 5;

		// 5. If the trimmed length of the item description is a multiple of 3,
		// multiply the price by 0.2 and round up to the nearest integer.
		// The result is the number of points earned.
		for (ItemBean itemBean : receipt.getItems()) {
			String trimmedDesc = itemBean.getShortDescription().trim();

			if (trimmedDesc.length() % 3 == 0) {
				double itemPrice = Double.parseDouble(itemBean.getPrice());
				points += (int) Math.ceil(itemPrice * 0.2);
			}
		}

		// 6. points if the day in the purchase date is odd..
		LocalDate purchaseDate = receipt.getPurchaseDate();
		if (purchaseDate.getDayOfMonth() % 2 == 1) points += 6;

		// 7. 10 points if the time of purchase is after 2:00pm and before 4:00pm.
		int hour = receipt.getPurchaseTime().getHour();
		if (hour >= 14 && hour < 16) points += 10;

		return points;
	}
}
