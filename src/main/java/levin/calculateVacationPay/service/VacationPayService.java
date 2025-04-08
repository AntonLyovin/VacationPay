package levin.calculateVacationPay.service;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface VacationPayService {

    double calculateVacationPay(double averageSalary, int vacationDays);

    double calculateVacationPayAdvanceDateAt(double averageSalary, LocalDate dateFrom, LocalDate dateTo);
}