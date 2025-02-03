package Receipt.Processor.Demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The main entry point for the Receipt Processor application.
 *
 * This is a Spring Boot application, and the @SpringBootApplication annotation:
 * - Enables auto-configuration.
 * - Scans components within the package.
 * - Acts as the main configuration class.
 */
@SpringBootApplication
public class ReceiptProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(ReceiptProcessorApplication.class, args);
    }
}