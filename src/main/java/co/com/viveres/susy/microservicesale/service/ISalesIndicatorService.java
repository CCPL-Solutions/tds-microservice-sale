package co.com.viveres.susy.microservicesale.service;

import co.com.viveres.susy.microservicesale.dto.SaleIndicatorRsDto;
import co.com.viveres.susy.microservicesale.dto.SalesGraphRsDto;
import co.com.viveres.susy.microservicesale.dto.TotalProductsSold;

import java.time.LocalDate;
import java.util.List;

public interface ISalesIndicatorService {

    SaleIndicatorRsDto getSalesIndicators();

    List<SalesGraphRsDto> getGraphs(int numDays, int numMonths);

    List<TotalProductsSold> getTotalProductsSold(LocalDate date);

}
