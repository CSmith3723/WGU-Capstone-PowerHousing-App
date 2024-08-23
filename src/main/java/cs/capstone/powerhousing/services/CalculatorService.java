package cs.capstone.powerhousing.services;

import cs.capstone.powerhousing.entities.Calculator;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService {


    public double calculateGrossWages(Calculator calculator){
        if(calculator.getWageType().equals("Hourly")){

            return calculator.getWageInput() * calculator.getHoursWorked() * 4;
        }
        else{
            return  calculator.getWageInput()/12;
        }
    }

    public double calculateNetWages(Calculator calculator){
        return calculator.getGrossMonthlyWages() - calculator.getMonthlyExpenses();
    }



}
