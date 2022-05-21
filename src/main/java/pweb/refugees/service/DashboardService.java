package pweb.refugees.service;



import pweb.refugees.domain.dto.BookingHolderDto;
import pweb.refugees.domain.dto.HouseDTO;
import pweb.refugees.domain.dto.BookingPeriodDTO;
import pweb.refugees.domain.dto.PreferencesRequestDTO;
import pweb.refugees.enums.BookingStatus;

import java.util.Set;

public interface DashboardService {

    void createHouse(HouseDTO houseView);

    HouseDTO getHouse(Long id);

    Set<HouseDTO> getHouses();

    Set<HouseDTO> getMyHouses();

    void createBooking(Long houseId, BookingPeriodDTO bookingPeriod);

    BookingHolderDto getBookings();

    void updateBooking(Long bookingId, BookingStatus bookingStatus);

    boolean checkForNewBookings();

    void updateUserRole(String role);

    Set<String> getCities();

    void updateHouse(HouseDTO houseDTO);


    Set<HouseDTO> getHousesFiltered(PreferencesRequestDTO preferencesRequestDTO);
}
