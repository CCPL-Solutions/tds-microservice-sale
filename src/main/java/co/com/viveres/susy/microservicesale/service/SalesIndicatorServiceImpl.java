package co.com.viveres.susy.microservicesale.service;

import co.com.viveres.susy.microservicesale.dto.SaleDaysReportSum;
import co.com.viveres.susy.microservicesale.dto.SaleIndicatorRsDto;
import co.com.viveres.susy.microservicesale.dto.SalesGraphRsDto;
import co.com.viveres.susy.microservicesale.entity.SaleEntity;
import co.com.viveres.susy.microservicesale.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SalesIndicatorServiceImpl implements ISalesIndicatorService {

    @Autowired
    private ISaleRepository saleRepository;

    @Override
    public SaleIndicatorRsDto getSalesIndicators() {

        List<SaleEntity> sales = this.saleRepository.findByDate(LocalDate.now());

        double total = sales.stream().mapToDouble(SaleEntity::getAmount).sum();

        return SaleIndicatorRsDto
                .builder()
                .totalAmount(BigDecimal.valueOf(total))
                .build();
    }

    @Override
    public List<SalesGraphRsDto> getGraphs(int numDays) {

        List<SaleDaysReportSum> sales = this.saleRepository.findAllGroup();

        List<BigDecimal> amountDays = getAmountDays(sales, numDays);
        List<String> labelListDays = getLabelListDays(sales, numDays);

        SalesGraphRsDto graphRsDto = SalesGraphRsDto
                .builder()
                .graphName("Total de ventas ultimos 5 días")
                .label("Total de venta diaria")
                .amounts(amountDays)
                .labels(labelListDays)
                .build();

        return Collections.singletonList(graphRsDto);
    }

    private List<BigDecimal> getAmountDays(List<SaleDaysReportSum> sales, int numDays) {
        List<BigDecimal> amountListDays = new ArrayList<>();
        sales.stream()
                .sorted(Comparator.comparing(SaleDaysReportSum::getDate))
                .collect(Collectors.toList())
                .stream().limit(numDays)
                .mapToDouble(SaleDaysReportSum::getSalesSum)
                .forEach(value -> amountListDays.add(BigDecimal.valueOf(value)));
        return amountListDays;
    }

    private List<String> getLabelListDays(List<SaleDaysReportSum> sales, int numDays) {
        List<String> labelListDays = new ArrayList<>();
        sales.stream()
                .sorted(Comparator.comparing(SaleDaysReportSum::getDate))
                .collect(Collectors.toList())
                .stream().limit(numDays)
                .map(SaleDaysReportSum::getDate)
                .forEach(value -> labelListDays.add(value.toString()));
        return labelListDays;
    }

}
