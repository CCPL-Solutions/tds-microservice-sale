package co.com.viveres.susy.microservicesale.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.viveres.susy.microservicesale.entity.SaleEntity;

@Repository
public interface ISaleRepository extends JpaRepository<SaleEntity, Long> {

	List<SaleEntity> findByDate(LocalDate date);

}
