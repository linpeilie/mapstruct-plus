package io.github.linpeilie;

import io.github.linpeilie.model.Employee;
import io.github.linpeilie.model.EmployeeDto;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class EmployeeMapperTest {

    @Autowired
    private Converter converter;

    @Test
    public void testMapDtoToEntity() {

        EmployeeDto teamLeader = employeeDto("Group Leader", null);

        EmployeeDto member1 = employeeDto("Member2", teamLeader);
        EmployeeDto member2 = employeeDto("Member2", teamLeader);
        teamLeader.setTeam(Arrays.asList(member1, member2));

        Employee teamLead = converter.convert(teamLeader, Employee.class);

        assertThat(teamLead).isNotNull();
        assertThat(teamLead.getReportsTo()).isNull();
        List<Employee> team = teamLead.getTeam();
        assertThat(team).hasSize(2);
        assertThat(team).extracting("reportsTo").containsExactly(teamLead, teamLead);
    }

    private EmployeeDto employeeDto(String name, EmployeeDto reportsTo) {
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setEmployeeName(name);
        employeeDto.setReportsTo(reportsTo);
        return employeeDto;
    }

    @Test
    public void testMapEntityToDto() {

        Employee teamLeader = employee("Group Leader", null);

        Employee member1 = employee("Member2", teamLeader);
        Employee member2 = employee("Member2", teamLeader);
        teamLeader.setTeam(Arrays.asList(member1, member2));

        EmployeeDto teamLead = converter.convert(teamLeader, EmployeeDto.class);

        assertThat(teamLead).isNotNull();
        assertThat(teamLead.getReportsTo()).isNull();
        List<EmployeeDto> team = teamLead.getTeam();
        assertThat(team).hasSize(2);
        assertThat(team).extracting("reportsTo").containsExactly(teamLead, teamLead);
    }

    private Employee employee(String name, Employee reportsTo) {
        Employee employee = new Employee();
        employee.setName(name);
        employee.setReportsTo(reportsTo);
        return employee;
    }

}
