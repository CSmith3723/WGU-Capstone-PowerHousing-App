package cs.capstone.powerhousing.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@Setter
public class Calculator {

    public Calculator(){}

    private List<String> wageTypes = new ArrayList<>(Arrays.asList("Hourly", "Salaried"));
    private String wageType;
    private double wageInput;
    private double hoursWorked;
    private double monthlyExpenses;
    private double grossMonthlyWages;
    private double netMonthlyWages;


}
