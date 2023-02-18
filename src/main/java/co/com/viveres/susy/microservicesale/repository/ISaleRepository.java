package co.com.viveres.susy.microservicesale.repository;

import java.time.LocalDate;
import java.util.List;

import co.com.viveres.susy.microservicesale.dto.SaleDaysReportSum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import co.com.viveres.susy.microservicesale.entity.SaleEntity;

@Repository
public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {
	
	List<SaleEntity> findByDate(LocalDate date);
	
	Page<SaleEntity> findByDate(LocalDate date, Pageable pageable);

	@Query(value = "SELECT new co.com.viveres.susy.microservicesale.dto.SaleDaysReportSum(s.date, SUM(s.amount)) FROM SaleEntity s GROUP BY s.date")
    List<SaleDaysReportSum> findAllGroup();
}
