package id.nano.challangespring.repository;

import id.nano.challangespring.entity.OrderDetail;
import id.nano.challangespring.entity.dto.OrderDetailDto;
import id.nano.challangespring.entity.dto.OrderDetailProductsDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetail, UUID>, JpaSpecificationExecutor<OrderDetail> {
    List<OrderDetail> findByOrder_Id(UUID id);

    @Query(name = "find_all_product_on_order",nativeQuery = true)
    List<OrderDetailProductsDto> getAllProductOnOrder(@Param("idOrder") UUID idOrder);

    @Procedure(procedureName = "get_order_details")
    int getAllProductOnOrderId(UUID idOrder);

}
