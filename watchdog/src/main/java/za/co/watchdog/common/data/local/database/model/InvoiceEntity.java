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
@Table(name = "Invoice")
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long invoiceId;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "clientId")
    private ClientEntity clientEntity;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "subscriptionId")
    private SubscriptionEntity subscriptionEntity;

    private Double amountDue;
    private Instant issueDate;
    private Instant dueDate;
    private String status;
}
