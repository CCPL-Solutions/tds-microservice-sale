package co.com.viveres.susy.microservicesale.service;

import co.com.viveres.susy.microservicesale.dto.SaleDaysReportSum;
import co.com.viveres.susy.microservicesale.dto.SaleIndicatorRsDto;
import co.com.viveres.susy.microservicesale.dto.SaleMonthsReportSum;
import co.com.viveres.susy.microservicesale.dto.SalesGraphRsDto;
import co.com.viveres.susy.microservicesale.entity.SaleEntity;
import co.com.viveres.susy.microservicesale.repository.ISaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
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
    public List<SalesGraphRsDto> getGraphs(int numDays, int numMonths) {

        SalesGraphRsDto graphDaysRsDto = getSalesDaysGraph(numDays);
        SalesGraphRsDto graphMonthRsDto = getSalesMonthsGraph(numMonths);

        return Arrays.asList(graphDaysRsDto, graphMonthRsDto);
    }

    private SalesGraphRsDto getSalesDaysGraph(int numDays) {
        List<SaleDaysReportSum> sales = this.saleRepository.findAllGroup();

        List<BigDecimal> amountDays = getAmountDays(sales, numDays);
        List<String> labelListDays = getLabelListDays(sales, numDays);

        return SalesGraphRsDto
                .builder()
                .graphName(String.format("Total de ventas ultimos %s días", numDays))
                .label("Total de ventas diarias")
                .amounts(amountDays)
                .labels(labelListDays)
                .build();
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

    private SalesGraphRsDto getSalesMonthsGraph(int numMonths) {

        List<SaleMonthsReportSum> sales = this.saleRepository.findAllGroupByMonth();

        List<BigDecimal> amount = getAmountMonths(sales, numMonths);
        List<String> labelList = getLabelListMonths(sales, numMonths);

        return SalesGraphRsDto
                .builder()
                .graphName(String.format("Total de ventas ultimos %s meses", numMonths))
                .label("Total de ventas mensual")
                .amounts(amount)
                .labels(labelList)
                .build();
    }

    private List<BigDecimal> getAmountMonths(List<SaleMonthsReportSum> sales, int numDays) {
        List<BigDecimal> amountListDays = new ArrayList<>();
        sales.stream()
                .sorted(Comparator.comparing(SaleMonthsReportSum::getDate))
                .collect(Collectors.toList())
                .stream().limit(numDays)
                .mapToDouble(SaleMonthsReportSum::getSalesSum)
                .forEach(value -> amountListDays.add(BigDecimal.valueOf(value)));
        return amountListDays;
    }

    private List<String> getLabelListMonths(List<SaleMonthsReportSum> sales, int numMonths) {
        List<String> labelListDays = new ArrayList<>();
        sales.stream()
                .sorted(Comparator.comparing(saleMonthsReportSum -> saleMonthsReportSum.getDate().toLocalDate()))
                .collect(Collectors.toList())
                .stream().limit(numMonths)
                .map(saleMonthsReportSum -> saleMonthsReportSum.getDate().toLocalDate())
                .forEach(value -> labelListDays.add(value.toString()));
        return labelListDays;
    }
}
