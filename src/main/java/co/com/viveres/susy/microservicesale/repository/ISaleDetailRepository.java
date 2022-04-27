package co.com.viveres.susy.microservicesale.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import co.com.viveres.susy.microservicesale.entity.SaleDetailEntity;

@Repository
public interface ISaleDetailRepository extends JpaRepository<SaleDetailEntity, Long> {

}
