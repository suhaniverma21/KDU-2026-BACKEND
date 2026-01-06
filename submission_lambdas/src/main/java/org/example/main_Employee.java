package org.example;

import java.util.List;
import java.util.Objects;

public class main_Employee {
    public static void main(String[] args)
    {
        List<Employee> sample_data=Employee.getSampleData();
        List<Employee> result=Employee.selectedEmp(sample_data,e -> e.getSalary() > 700000 &&
             Objects.equals(e.getDepartment(), "ENGINEERING"));
        System.out.println(result);
        List<String> upperCaseNamesReport = sample_data.stream()
                .map(e -> e.getName().toUpperCase())
                .toList();
        System.out.println(upperCaseNamesReport);
        Double total_salary = sample_data.stream()
                .map(Employee::getSalary)
                .reduce(Double.valueOf(Integer.valueOf(0)), Double::sum);
        System.out.println(total_salary);




    }
}
