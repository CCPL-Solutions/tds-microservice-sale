package co.com.viveres.susy.microservicesale.controller;

import co.com.viveres.susy.microservicesale.api.ISalesIndicator;
import co.com.viveres.susy.microservicesale.dto.SaleIndicatorRsDto;
import co.com.viveres.susy.microservicesale.dto.SalesGraphRsDto;
import co.com.viveres.susy.microservicesale.dto.TotalProductsSold;
import co.com.viveres.susy.microservicesale.service.ISalesIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/v1/sale-indicators")
public class SalesIndicatorApiImpl implements ISalesIndicator {

    @Autowired
    private ISalesIndicatorService salesIndicatorService;

    @Override
    public ResponseEntity<SaleIndicatorRsDto> getSalesIndicators() {
        SaleIndicatorRsDto response = this.salesIndicatorService.getSalesIndicators();
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<SalesGraphRsDto>> getGraphs(int numDays, int numMonths) {
        List<SalesGraphRsDto> response = this.salesIndicatorService.getGraphs(numDays, numMonths);
        return ResponseEntity.ok(response);
    }

    @Override
    public ResponseEntity<List<TotalProductsSold>> getTotalProductsSold(LocalDate date) {
        List<TotalProductsSold> response = this.salesIndicatorService.getTotalProductsSold(date);
        return ResponseEntity.ok(response);
    }

}
