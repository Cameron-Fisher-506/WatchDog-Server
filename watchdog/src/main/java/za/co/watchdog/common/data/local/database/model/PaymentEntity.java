package za.co.watchdog.common.data.local.database.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "payment")
public class PaymentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long paymentId;

    @ManyToOne
    @JoinColumn(name = "invoiceId")
    private InvoiceEntity invoice;

    private Double amountPaid;
    private Instant paymentDate;
    private String paymentMethod;
    private String transactionReference;
    private String status;
}
