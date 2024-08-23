package cs.capstone.powerhousing.services;

import cs.capstone.powerhousing.dao.ProfileRepository;
import cs.capstone.powerhousing.entities.SavedProfile;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ProfileServiceTest {

    @Mock
    private ProfileRepository profileRepository;

    @InjectMocks
    private ProfileService profileService;

    private SavedProfile testProfile;
    private SavedProfile testProfile2;

    @BeforeEach
    public void testSetUp(){
        MockitoAnnotations.openMocks(this);
        testProfile = new SavedProfile();
        testProfile.setId(1L);
        testProfile.setUsername("Chris");
        testProfile.setNeighborhood("Citrus Park");
        testProfile.setHousingType("House");
        testProfile.setHousingPrice(440000);
        testProfile.setGrossMonthlyWages(5000);
        testProfile.setMonthlyExpenses(3000);
        testProfile.setNetMonthlyWages(2000);

        testProfile2 = new SavedProfile();
        testProfile2.setId(2L);
        testProfile2.setUsername("Madeline");
        testProfile2.setNeighborhood("Westchase");
        testProfile2.setHousingType("One Bedroom Apartment");
        testProfile2.setHousingPrice(1360);
        testProfile2.setGrossMonthlyWages(2500);
        testProfile2.setMonthlyExpenses(1000);
        testProfile2.setNetMonthlyWages(1500);

        when(profileRepository.findSavedProfileByUsername("Chris"))
                .thenReturn(testProfile);
        when(profileRepository.findSavedProfileByUsername("Madeline"))
                .thenReturn(testProfile2);
        when(profileRepository.save(testProfile)).thenReturn(testProfile);
        when(profileRepository.save(testProfile2)).thenReturn(testProfile2);
    }



    @Test
    void findAllProfiles() {

        List<SavedProfile> testProfiles = Arrays.asList(testProfile,testProfile2);

        when(profileRepository.findAll()).thenReturn(testProfiles);

        List<SavedProfile> result = profileService.findAllProfiles();

        assertEquals(2, result.size());
        assertEquals("Chris", result.get(0).getUsername());
        assertEquals("Madeline", result.get(1).getUsername());

    }

    @Test
    void deleteSavedProfileById(){
        Long testId = 1L;
        profileService.deleteSavedProfileById(testId);
        verify(profileRepository).deleteById(testId);
    }

    @Test
    void findSavedProfileByUsername() {
        SavedProfile profile = profileService.findSavedProfileByUsername("Chris");

        assertEquals("Chris", profile.getUsername());
    }
}