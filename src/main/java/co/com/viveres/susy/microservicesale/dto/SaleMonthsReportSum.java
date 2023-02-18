package co.com.viveres.susy.microservicesale.dto;

import java.time.LocalDateTime;

public interface SaleMonthsReportSum {

    LocalDateTime getDate();

    Double getSalesSum();

}
