package co.com.viveres.susy.microservicesale.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "SALE")
public class SaleEntity {

	@Id
	@SequenceGenerator(name = "SEQ_SALE_ID", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SALE_ID")
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @Column(name = "USER_NAME", unique = false, nullable = false)
    private String userName;

    @Column(name = "AMOUNT", unique = false, nullable = false)
    private Double amount;

    @Column(name = "DATE", unique = false, nullable = false)
    private LocalDate date;
    
    @Column(name = "TIME", unique = false, nullable = false)
    private LocalTime time;

    @OneToMany(mappedBy = "sale", cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<SaleDetailEntity> saleDetail;

    public void addProduct(SaleDetailEntity detail) {
        if (saleDetail == null)
        	saleDetail = new ArrayList<>();
        saleDetail.add(detail);
        detail.setSale(this);
    }

}
