package co.com.viveres.susy.microservicesale.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "SALE_DETAIL")
public class SaleDetailEntity {

    @Id
    @SequenceGenerator(name = "SEQ_SALE_DETAIL_ID", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SEQ_SALE_DETAIL_ID")
    @Column(name = "ID", unique = true, nullable = false)
    private Long id;

    @ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    @JoinColumn(name = "ID_SALE_FK", unique = false, nullable = false)
    private SaleEntity sale;

    @Column(name = "ID_PRODUCT_FK", unique = false, nullable = false)
    private Long productId;

    @Column(name = "NUM_ITEMS", unique = false, nullable = false)
    private Integer numberItems;

    @Column(name = "PRICE_UNIT", unique = false, nullable = false)
    private Double priceUnit;

}
