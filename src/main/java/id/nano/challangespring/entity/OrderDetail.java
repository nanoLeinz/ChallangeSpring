package id.nano.challangespring.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import id.nano.challangespring.entity.dto.OrderDetailProductsDto;
import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.hibernate.annotations.Where;

import jakarta.validation.constraints.*;

import java.io.Serializable;
import java.util.UUID;


@Entity
@Data
@Getter
@Setter
@Table(name = "order_detail")
@Where(clause = "deleted_date is null")
@NamedNativeQuery(name = "find_all_product_on_order",
        query = "SELECT c.merchant_name, b.product_name, a.quantity, b.price,  a.quantity * b.price as total  FROM order_detail a LEFT JOIN product b on a.product_id = b.id LEFT JOIN merchant c on b.merchant_id = c.id WHERE a.order_id = (:idOrder)",
        resultSetMapping = "orderdetailproductdto"
        )
@SqlResultSetMapping(name = "orderdetailproductdto",
        classes = @ConstructorResult(
                targetClass = OrderDetailProductsDto.class,
                columns = {
                        @ColumnResult(name = "merchant_name", type = String.class),
                        @ColumnResult(name = "product_name", type = String.class),
                        @ColumnResult(name = "quantity", type = Integer.class),
                        @ColumnResult(name = "price", type = Integer.class),
                        @ColumnResult(name = "total", type = Integer.class)
                }
        ))
public class OrderDetail extends AbstractDate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @NotNull
    private Integer quantity;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Order order;
}
