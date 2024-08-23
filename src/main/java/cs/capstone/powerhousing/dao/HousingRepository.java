package cs.capstone.powerhousing.dao;

import cs.capstone.powerhousing.entities.Housing;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HousingRepository extends JpaRepository<Housing, String> {

    Housing findByNeighborhood(String neighborhood);

//    @Query("select h.medianOneBedApt from Housing h where h.neighborhood = :neighborhood")
//    int oneBedPriceByNeighborhood(String neighborhood);

}
