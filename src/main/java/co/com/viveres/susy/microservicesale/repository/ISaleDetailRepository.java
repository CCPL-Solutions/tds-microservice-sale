package co.com.viveres.susy.microservicesale.repository;

import co.com.viveres.susy.microservicesale.dto.SaleDaysReportSum;
import co.com.viveres.susy.microservicesale.dto.TotalProductsSold;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.viveres.susy.microservicesale.entity.SaleDetailEntity;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISaleDetailRepository extends JpaRepository<SaleDetailEntity, Long> {

    @Query(value = "SELECT p.description AS product, SUM(sd.num_items) AS totalItems " +
            "FROM \"SALE_DETAILS\" sd " +
            "INNER JOIN \"PRODUCTS\" p ON sd.id_product_fk = p.id " +
            "INNER JOIN \"SALES\" s ON sd.id_sale_fk = s.id " +
            "WHERE " +
            "s.date = ?1 " +
            "GROUP BY p.description", nativeQuery = true)
    List<TotalProductsSold> findTotalProductsSold(LocalDate date);

}
