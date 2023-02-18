package co.com.viveres.susy.microservicesale.repository;

import co.com.viveres.susy.microservicesale.dto.SaleDaysReportSum;
import co.com.viveres.susy.microservicesale.dto.SaleMonthsReportSum;
import co.com.viveres.susy.microservicesale.entity.SaleEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {

    List<SaleEntity> findByDate(LocalDate date);

    Page<SaleEntity> findByDate(LocalDate date, Pageable pageable);

    @Query(value = "SELECT new co.com.viveres.susy.microservicesale.dto.SaleDaysReportSum(s.date, SUM(s.amount)) FROM SaleEntity s GROUP BY s.date")
    List<SaleDaysReportSum> findAllGroup();

    @Query(value = "SELECT date_trunc('month', s.date) AS date, SUM(s.amount) as salesSum FROM \"SALES\" s GROUP BY 1", nativeQuery = true)
    List<SaleMonthsReportSum> findAllGroupByMonth();

}
