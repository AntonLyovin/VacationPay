package levin.calculateVacationPay.service;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class VacationPayServiceImpl implements VacationPayService {
    @PostConstruct
    public void init() {
        List<Date> holidays = new ArrayList<>();
        holidays.add(new GregorianCalendar(2024, 12, 31).getTime());
        this.holidays = holidays;
    }

    private List<Date> holidays = new ArrayList<>();

    @Override
    public double calculateVacationPay(double averageSalary, int vacationDays) {
        return dailySalary(averageSalary) * vacationDays;
    }

    @Override
    public double calculateVacationPayAdvance(double averageSalary, List<Date> vacationsDays) {
        long days = countWorkDays(vacationsDays);
        return dailySalary(averageSalary) * days;
    }

    @Override
    public double calculateVacationPayAdvanceDateAt(double averageSalary, LocalDate dateFrom, LocalDate dateTo) {
        int days = (int) DAYS.between(dateFrom, dateTo);
        List<LocalDate> dates = new ArrayList<>();
        while (!dateFrom.isAfter(dateTo)) {
            dates.add(dateFrom);
            dateFrom = dateFrom.plusDays(1);
        }
        int count = countMatches(dates, holidays);
        int result = days-count;


        return dailySalary(averageSalary) * result;
    }

    private long countWorkDays(List<Date> vacationsDays) {
        return vacationsDays.stream().filter(i -> holidays.contains(i)).count();
    }

    private double dailySalary(double averageSalary) {
        return averageSalary / 29.3;
    }

    private int countMatches(List<LocalDate> dates, List<Date> holidays) {
        int count = 0;
        ArrayList<LocalDate> matchedItems = new ArrayList<>();

        for (LocalDate item : dates) {
            if (holidays.contains(item) && !matchedItems.contains(item)) {
                count++;
                matchedItems.add(item);
            }

            return count;
        }

        return count;
    }
}
