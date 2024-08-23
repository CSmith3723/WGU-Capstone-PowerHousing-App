package cs.capstone.powerhousing.services;


import cs.capstone.powerhousing.dao.ProfileRepository;
import cs.capstone.powerhousing.entities.SavedProfile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProfileService {

    private ProfileRepository profileRepository;

    public ProfileService(ProfileRepository profileRepository) {
        this.profileRepository = profileRepository;
    }


    public void saveProfile(SavedProfile savedProfile) {
        profileRepository.save(savedProfile);

    }

    public void deleteSavedProfileById(Long id) {
        profileRepository.deleteById(id);
    }


    public List<SavedProfile> findAllProfiles() {
        return profileRepository.findAll();
    }

    public List<SavedProfile> listAll(String keyword) {
        if (keyword != null) {
            return profileRepository.search(keyword);
        }
        return profileRepository.findAll();
    }

    public SavedProfile findSavedProfileByUsername(String username) {
        return profileRepository.findSavedProfileByUsername(username);
    }

    public List<SavedProfile> findAllByUsername(String username) {
        return profileRepository.findAllByUsername(username);
    }

}
