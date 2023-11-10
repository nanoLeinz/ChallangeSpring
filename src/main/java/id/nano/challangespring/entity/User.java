package id.nano.challangespring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Where;

import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Data
@Getter
@Setter
@Table(name = "users")
@Where(clause = "deleted_date is null")
public class User extends AbstractDate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    @NotBlank
    private String username;

    @NotNull
    @NotBlank
    @Email(message = "Insert a valid email address")
    @Column(name = "email_address")
    private String emailAddress;

    @NotNull
    @NotBlank
    @JsonIgnore
    private String password;
}
