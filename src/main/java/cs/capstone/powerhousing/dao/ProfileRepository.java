package cs.capstone.powerhousing.dao;

import cs.capstone.powerhousing.entities.SavedProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileRepository extends JpaRepository<SavedProfile,Long> {

    SavedProfile findSavedProfileByUsername(String username);

    List<SavedProfile> findAllByUsername(String username);


    @Query("SELECT sp FROM SavedProfile sp WHERE CONCAT(sp.username, ' ', sp.neighborhood, ' ', sp.housingType) LIKE %?1%")
    List<SavedProfile> search(String keyword);


}
