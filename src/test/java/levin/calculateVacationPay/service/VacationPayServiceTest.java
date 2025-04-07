package levin.calculateVacationPay.service;


import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;
import org.junit.jupiter.params.provider.ArgumentsSource;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class VacationPayServiceTest {

    @ParameterizedTest
    @ArgumentsSource(VacationArgumentsProvider.class)
    public void calculateVacationPayTest(double averageSalary, int vacationDays, double expected, double delta) {
        VacationPayService vacationPayService = new VacationPayServiceImpl();
        assertEquals(expected, vacationPayService.calculateVacationPay(averageSalary, vacationDays), delta);

    }
}

class VacationArgumentsProvider implements ArgumentsProvider {
    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext context) {
        return Stream.of(
                Arguments.of(1000, 1, 33.333333333333336, 0),
                Arguments.of(100000, 12, 40000.0, 0)
        );
    }
}
