package co.com.viveres.susy.microservicesale.api;

import co.com.viveres.susy.microservicesale.dto.SaleIndicatorRsDto;
import co.com.viveres.susy.microservicesale.dto.SalesGraphRsDto;
import co.com.viveres.susy.microservicesale.dto.TotalProductsSold;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.ws.rs.QueryParam;
import java.time.LocalDate;
import java.util.List;

public interface ISalesIndicator {

    @GetMapping(value = "")
    ResponseEntity<SaleIndicatorRsDto> getSalesIndicators();

    @GetMapping(value = "/graphs")
    ResponseEntity<List<SalesGraphRsDto>> getGraphs(@QueryParam("numDays") int numDays, @QueryParam("numMonths") int numMonths);

    @GetMapping(value = "/graphs/dona")
    ResponseEntity<List<TotalProductsSold>> getTotalProductsSold(@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                                                 @RequestParam("date") LocalDate date);

}
