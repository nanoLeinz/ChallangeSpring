package id.nano.challangespring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data
@Getter
@Setter
@Table(name = "orders")
@Where(clause = "deleted_date is null")
public class Order extends AbstractDate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @NotBlank
    @Column(name = "destination_address")
    private String destinationAddress;


    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "order_time", nullable = false)
    @CreationTimestamp
    private Date orderTime;

    @NotNull
    @NotBlank
    private Boolean completed;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private User user;
}
