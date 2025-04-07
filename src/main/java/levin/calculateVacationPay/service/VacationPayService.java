package levin.calculateVacationPay.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public interface VacationPayService {

    double calculateVacationPay(double averageSalary, int vacationDays);
    double calculateVacationPayAdvance(double averageSalary, List<Date> vacationsDays);
    public double calculateVacationPayAdvanceDateAt(double averageSalary, LocalDate dateFrom, LocalDate dateTo);
}