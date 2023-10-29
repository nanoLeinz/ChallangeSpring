package id.nano.challangespring.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@Table(name = "merchant")
@Where(clause = "deleted_date is null")
public class Merchant extends AbstractDate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull(message = "merchant Name Cannot Be Null")
    @NotBlank(message = "merchant Name Cannot Be Blank")
    @Column(name = "merchant_name")
    private String merchantName;

    @NotNull
    @NotBlank
    @Column(name = "merchant_location")
    private String merchantLocation;

    @NotNull
    private Boolean open;
}
