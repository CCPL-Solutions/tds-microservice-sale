package co.com.viveres.susy.microservicesale.api;

import co.com.viveres.susy.microservicesale.dto.SaleIndicatorRsDto;
import co.com.viveres.susy.microservicesale.dto.SalesGraphRsDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import javax.ws.rs.QueryParam;
import java.util.List;

public interface ISalesIndicator {

    @GetMapping(value = "")
    ResponseEntity<SaleIndicatorRsDto> getSalesIndicators();

    @GetMapping(value = "/graphs")
    ResponseEntity<List<SalesGraphRsDto>> getGraphs(@QueryParam("numDays") int numDays);

}
