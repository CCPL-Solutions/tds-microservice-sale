package co.com.viveres.susy.microservicesale.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
public class SaleDaysReportSum {
    private LocalDate date;
    private Double salesSum;
}
