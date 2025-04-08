package levin.calculateVacationPay.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@UtilityClass
public class DateUtils {

    public static List<LocalDate> getDatesFromInterval(LocalDate dateFrom, LocalDate dateTo) {
        return dateFrom.datesUntil(dateTo.plusDays(1))
                .collect(Collectors.toList());
    }

    public static List<LocalDate> getDateListByStringList(List<String> stringDates) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return stringDates.stream()
                .map(i -> LocalDate.parse(i, formatter))
                .collect(Collectors.toList());
    }
}
