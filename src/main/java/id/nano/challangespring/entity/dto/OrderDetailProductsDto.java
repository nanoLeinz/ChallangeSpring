package id.nano.challangespring.entity.dto;

import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@Data
public class OrderDetailProductsDto {
    private final String merchant;
    private final String product;
    private final Integer quantity;
}
