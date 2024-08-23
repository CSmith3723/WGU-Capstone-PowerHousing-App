package cs.capstone.powerhousing.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="saved_profiles")
public class SavedProfile {

    public SavedProfile(){}

    public SavedProfile(String username, String neighborhood,
                        String housingType, int housingPrice, double grossMonthlyWages,
                        double monthlyExpenses, double netMonthlyWages) {
        this.username = username;
        this.neighborhood = neighborhood;
        this.housingType = housingType;
        this.housingPrice = housingPrice;
        this.grossMonthlyWages = grossMonthlyWages;
        this.monthlyExpenses = monthlyExpenses;
        this.netMonthlyWages = netMonthlyWages;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="username")
    private String username;

    @Column(name="neighborhood")
    private String neighborhood;

    @Column(name="housing_type")
    private String housingType;

    @Column(name="housing_price")
    private int housingPrice;

    @Column(name="gross_wages")
    private double grossMonthlyWages;

    @Column(name="expenses")
    private double monthlyExpenses;

    @Column(name="net_wages")
    private double netMonthlyWages;


}
