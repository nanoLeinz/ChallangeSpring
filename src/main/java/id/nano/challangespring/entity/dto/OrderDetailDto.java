package id.nano.challangespring.entity.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.UUID;

/**
 * DTO for {@link id.nano.challangespring.entity.OrderDetail}
 */
@AllArgsConstructor
@Getter
@Setter
public class OrderDetailDto implements Serializable {
    @NotNull(message = "productId cannot be null")
    private final UUID productId;

    @NotNull(message = "Quantity cannot be null")
    private final Integer quantity;

}