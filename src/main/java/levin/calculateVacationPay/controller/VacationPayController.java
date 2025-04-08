package levin.calculateVacationPay.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import levin.calculateVacationPay.service.VacationPayService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.Min;
import java.time.LocalDate;

@RestController
@RequiredArgsConstructor
@FieldDefaults(makeFinal = true, level = AccessLevel.PRIVATE)
@Validated
@Tag(name = "VacationPayAPI", description = "Контроллер для расчета отпускных")
@RequestMapping("/api/v1/vacationPay")
public class VacationPayController {

    VacationPayService vacationPayService;

    @GetMapping("/calculate/{averageSalary}/{vacationDays}")
    @Validated
    @Operation(
            summary = "Расчет отпускных",
            description = "Позволяет рассчитать количество отпускных"
    )
    public Double calculateVacationPay(@PathVariable("averageSalary")
                                       @Parameter(description = "Средняя зарплата")
                                       @Min(1)
                                       double averageSalary,
                                       @PathVariable("vacationDays")
                                       @Parameter(description = "Количество дней")
                                       @Min(1)
                                       int vacationDays) {
        return vacationPayService.calculateVacationPay(averageSalary, vacationDays);
    }

    @GetMapping("/calculateByDays/{averageSalary}/{dateFrom}/{dateTo}")
    @Validated
    @Operation(
            summary = "Расчет отпускных",
            description = "Позволяет рассчитать количество отпускных"
    )
    public Double calculateVacationPay(@PathVariable("averageSalary")
                                       @Parameter(description = "Средняя зарплата")
                                       @Min(1)
                                       double averageSalary,
                                       @RequestParam("dateFrom") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       @Parameter(description = "Дата начала отпуска в формате: YYYY-MM-DD")
                                       LocalDate dateFrom,
                                       @RequestParam("dateTo") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
                                       @Parameter(description = "Дата окончания отпуска в формате: YYYY-MM-DD")
                                       LocalDate dateTo

    ) {
        return vacationPayService.calculateVacationPayAdvanceDateAt(averageSalary, dateFrom, dateTo);
    }
}