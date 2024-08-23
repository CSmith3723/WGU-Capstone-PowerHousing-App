package cs.capstone.powerhousing.entities;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name="neighborhoods")
public class Housing {

    public Housing(){}

    @Id
    @Column(name="neighborhood")
    private String neighborhood;

    @Column(name="median_home_price")
    private int medianHomePrice;

    @Column(name="median_onebr_apt_price")
    private int medianOneBedApt;

    @Column(name="median_twobr_apt_price")
    private int medianTwoBedApt;

    @Transient
    private int currentPriceByHousingType;

    @Transient
    private String housingType;

}
