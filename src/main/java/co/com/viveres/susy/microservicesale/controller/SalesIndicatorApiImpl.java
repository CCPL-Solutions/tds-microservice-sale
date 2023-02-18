package co.com.viveres.susy.microservicesale.controller;

import co.com.viveres.susy.microservicesale.api.ISalesIndicator;
import co.com.viveres.susy.microservicesale.dto.SaleIndicatorRsDto;
import co.com.viveres.susy.microservicesale.dto.SalesGraphRsDto;
import co.com.viveres.susy.microservicesale.service.ISalesIndicatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<List<SalesGraphRsDto>> getGraphs(int numDays) {
        List<SalesGraphRsDto> response = this.salesIndicatorService.getGraphs(numDays);
        return ResponseEntity.ok(response);
    }

}