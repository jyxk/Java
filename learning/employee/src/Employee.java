import java.time.LocalDate;

public class Employee {
    private String name;
    private double salary;
    private LocalDate hireday;

    public Employee(String n, double s, int year, int month, int day) {
        name = n;
        salary = s;
        hireday = LocalDate.of(year, month, day);
    }

    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public LocalDate getHireday() {
        return hireday;
    }

    public void raiseSalary(double byPercent) {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
