package cs.capstone.powerhousing.services;

import cs.capstone.powerhousing.dao.HousingRepository;
import cs.capstone.powerhousing.entities.Housing;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@Service
public class HousingService{

    HousingRepository housingRepository;

    public HousingService(HousingRepository housingRepository){
        this.housingRepository = housingRepository;
    }

    public List<String> neighborhoodsList() {
        List<Housing> neighborhoodInfo = housingRepository.findAll();

        return neighborhoodInfo.stream()
                .map(Housing::getNeighborhood)
                .collect(Collectors.toList());
    }

    public List<Housing> findAll() {
        return housingRepository.findAll();
    }

    public List<Integer> oneBRAptPrices(){
        List<Housing> oneBedAptPrices = housingRepository.findAll();

        return oneBedAptPrices.stream()
                .map(Housing::getMedianOneBedApt)
                .collect(Collectors.toList());
    }

    public List<Integer> twoBRAptPrices(){
        List<Housing> twoBedAptPrices = housingRepository.findAll();

        return twoBedAptPrices.stream()
                .map(Housing::getMedianOneBedApt)
                .collect(Collectors.toList());
    }

    public List<Integer> housingPrices(){
        List<Housing> housingPrices = housingRepository.findAll();

        return housingPrices.stream()
                .map(Housing::getMedianHomePrice)
                .collect(Collectors.toList());
    }


    public Housing findByNeighborhood(String neighborhood){
       return housingRepository.findByNeighborhood(neighborhood);
    }

    public int oneBedPriceByNeighborhood(String neighborhood) {
       return housingRepository.findByNeighborhood(neighborhood).getMedianOneBedApt();
    }

    public int twoBedPriceByNeighborhood(String neighborhood){
        return housingRepository.findByNeighborhood(neighborhood).getMedianTwoBedApt();
    }


    public int homePricebyNeighborhood(String neighborhood){
        return housingRepository.findByNeighborhood(neighborhood).getMedianHomePrice();
    }


    public int priceByHousingType(String neighborhood, String housingType){

        if(housingType.equals("One Bedroom Apartment")){
            return oneBedPriceByNeighborhood(neighborhood);
        }
        if(housingType.equals("Two Bedroom Apartment")){
            return twoBedPriceByNeighborhood(neighborhood);
        }
        if(housingType.equals("House")){
            return homePricebyNeighborhood(neighborhood);
        }
        else{
            return -1;
        }

    }


}
