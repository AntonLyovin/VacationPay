package levin.calculateVacationPay.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static levin.calculateVacationPay.util.DateUtils.getDateListByStringList;
import static levin.calculateVacationPay.util.DateUtils.getDatesFromInterval;

@Service
public class VacationPayServiceImpl implements VacationPayService {

    @Value("${arrayOfStrings}")
    private List<String> stringDates;
    private List<LocalDate> holidays = new ArrayList<>();

    @PostConstruct
    private void init() {
        holidays = getDateListByStringList(stringDates);
    }

    @Override
    public double calculateVacationPay(double averageSalary, int vacationDays) {
        return dailySalary(averageSalary) * vacationDays;
    }

    @Override
    public double calculateVacationPayAdvanceDateAt(double averageSalary, LocalDate dateFrom, LocalDate dateTo) {
        return dailySalary(averageSalary) * countWorkDays(getDatesFromInterval(dateFrom, dateTo));
    }

    private long countWorkDays(List<LocalDate> vacationsDays) {
        return vacationsDays.stream().filter(i -> !holidays.contains(i)).count();
    }

    private double dailySalary(double averageSalary) {
        return averageSalary / 29.3;
    }
}
