package io.github.linpeilie.me.conditional.expression;

public class StaticUtil {

    static boolean isAmericanCitizen(EmployeeDto employeeDto) {
        return "US".equals(employeeDto.getCountry());
    }

    static boolean isBritishCitizen(EmployeeDto employeeDto) {
        return "UK".equals(employeeDto.getCountry());
    }

}
