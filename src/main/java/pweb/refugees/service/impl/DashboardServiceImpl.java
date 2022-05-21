package pweb.refugees.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pweb.refugees.domain.Booking;
import pweb.refugees.domain.House;
import pweb.refugees.domain.ItemControl;
import pweb.refugees.domain.User;
import pweb.refugees.domain.dto.*;
import pweb.refugees.enums.BookingStatus;
import pweb.refugees.enums.Role;
import pweb.refugees.mappers.GeneralMapperComponent;
import pweb.refugees.repository.BookingRepository;
import pweb.refugees.repository.HouseRepository;
import pweb.refugees.repository.ItemControlRepository;
import pweb.refugees.repository.UserRepository;
import pweb.refugees.service.CurrentUserService;
import pweb.refugees.service.DashboardService;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class DashboardServiceImpl implements DashboardService {

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private CurrentUserService currentUserService;

    @Autowired
    private BookingRepository bookingRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private GeneralMapperComponent generalMapperComponent;

    @Autowired
    private ItemControlRepository itemControlRepository;


    @Override
    public void createHouse(HouseDTO houseView) {
        User currentUser = currentUserService.getCurrentUserDetails();
        House houseToPersist = generalMapperComponent.dtoToHouse(houseView);
        houseToPersist.setOwner(currentUser);
        houseRepository.save(houseToPersist);
    }

    @Override
    public HouseDTO getHouse(Long id) {
        House house = houseRepository.getById(id);
        return generalMapperComponent.houseToDto(house);
    }

    @Override
    public Set<HouseDTO> getHouses() {
        List<House> houses = houseRepository.findAll();
        return houses.stream().map(h -> generalMapperComponent.houseToDto(h)).collect(Collectors.toSet());
    }

    @Override
    public Set<HouseDTO> getMyHouses() {
        User currentUser = currentUserService.getCurrentUserDetails();
        List<House> houses = houseRepository.findAllByOwner(currentUser);
        return houses.stream().map(h -> generalMapperComponent.houseToDto(h)).collect(Collectors.toSet());
    }

    @Override
    public void createBooking(Long houseId, BookingPeriodDTO bookingPeriod) {

        User currentUser = currentUserService.getCurrentUserDetails();
        House rentHouse = houseRepository.getById(houseId);
        Booking bookingToPersist = new Booking(bookingPeriod.getStartDate(), bookingPeriod.getEndDate(),
                currentUser, bookingPeriod.getPhone(), bookingPeriod.getMessage(), bookingPeriod.getPeopleNo(),
               BookingStatus.PENDING, rentHouse);
        bookingToPersist.setGuestPhone(bookingPeriod.getPhone());
        bookingToPersist.setGuestMessage(bookingPeriod.getMessage());
        updateControlTableForUser(rentHouse);
        bookingRepository.save(bookingToPersist);

}

    private void updateControlTableForUser(House rentHouse) {
        Optional<ItemControl> itemControl = itemControlRepository.findByOwner(rentHouse.getOwner());
        if (itemControl.isPresent()) {
            ItemControl itemControlToUpdate = itemControl.get();
            long currentBookingsCount = itemControlToUpdate.getCurrentBookingsCount();
            itemControlToUpdate.setOldBookingsCount(currentBookingsCount);
            long newBookingsCount = currentBookingsCount + 1;
            itemControlToUpdate.setCurrentBookingsCount(newBookingsCount);
        } else {
            ItemControl newItemControl = new ItemControl();
            newItemControl.setCurrentBookingsCount(1L);
            newItemControl.setOldBookingsCount(0L);
            newItemControl.setOwner(rentHouse.getOwner());
            itemControlRepository.save(newItemControl);

        }
    }

    @Override
    public BookingHolderDto getBookings() {
        User currentUser = currentUserService.getCurrentUserDetails();
        Set<Booking> bookings;
        if (currentUser.getRole() == Role.ROLE_HOST) {
            bookings = bookingRepository.findAllByRentHouse_Owner(currentUser);
        } else {
            bookings = bookingRepository.findAllByGuest(currentUser);
        }

        Set<BookingDTO> bookingDTOSet = bookings.stream().map(b -> generalMapperComponent.bookingToDto(b)).collect(Collectors.toSet());
        Set<BookingDTO> bookingsApproved = bookingDTOSet.stream()
                .filter(b -> b.getBookingStatus().equals(BookingStatus.APPROVED))
                .collect(Collectors.toSet());
        Set<BookingDTO> pendingBookings = bookingDTOSet.stream()
                .filter(b -> b.getBookingStatus().equals(BookingStatus.PENDING))
                .collect(Collectors.toSet());
        BookingHolderDto bookingHolderDto = new BookingHolderDto();
        bookingHolderDto.setApprovedNotifications(bookingsApproved);
        bookingHolderDto.setPendingNotifications(pendingBookings);
        markItemControlCountAsViewed(currentUser);
        return bookingHolderDto;
    }

    private void markItemControlCountAsViewed(User currentUser) {
        Optional<ItemControl> itemControl = itemControlRepository.findByOwner(currentUser);
        if (itemControl.isPresent()) {
            ItemControl itemControlToUpdate = itemControl.get();
            itemControlToUpdate.setOldBookingsCount(itemControlToUpdate.getCurrentBookingsCount());
            itemControlRepository.save(itemControlToUpdate);
        }
    }

    @Override
    public void updateBooking(Long bookingId, BookingStatus bookingStatus) {
        Booking bookingToUpdate = bookingRepository.getById(bookingId);
        bookingToUpdate.setBookingStatus(bookingStatus);
        bookingRepository.save(bookingToUpdate);
    }

    @Override
    public boolean checkForNewBookings() {
        User currentUser = currentUserService.getCurrentUserDetails();
        Optional<ItemControl> itemControl = itemControlRepository.findByOwner(currentUser);
        return itemControl
                .map(control -> control.getCurrentBookingsCount().equals(control.getOldBookingsCount()))
                .orElse(false);
    }

    @Override
    public void updateUserRole(String role) {
        User currentUser = currentUserService.getCurrentUserDetails();
        currentUser.setRole(Role.valueOf(role));
        userRepository.save(currentUser);
    }

    @Override
    public Set<String> getCities() {
        return houseRepository.findAll().stream().map(h -> h.getCity()).collect(Collectors.toSet());
    }

    @Override
    public Set<HouseDTO> getHousesFiltered(PreferencesRequestDTO preferencesRequestDTO) {
        Set<House> houses = houseRepository.findAllHousesFiltered(preferencesRequestDTO.getCity(), preferencesRequestDTO.getNumPeople(),
                preferencesRequestDTO.getNumDays());
        return houses.stream().map(h -> generalMapperComponent.houseToDto(h)).collect(Collectors.toSet());
    }


    @Override
    public void updateHouse(HouseDTO houseDTO) {
        House house = houseRepository.getById(houseDTO.getId());
        if (!Objects.isNull(houseDTO.getAddress())) {
            house.setAddress(houseDTO.getAddress());
        }
        if (!Objects.isNull(houseDTO.getCity())) {
            house.setAddress(houseDTO.getCity());
        }
        if (!Objects.isNull(houseDTO.getCapacity())) {
            house.setCapacity(houseDTO.getCapacity());
        }
        if (!Objects.isNull(houseDTO.getPhone())) {
            house.setPhone(houseDTO.getPhone());
        }
        if (!Objects.isNull(houseDTO.getImage())) {
            house.setImage(houseDTO.getImage());
        }
        if (!Objects.isNull(houseDTO.getBookingPeriod())) {
            house.setBookingPeriod(houseDTO.getBookingPeriod());
        }
        if (!Objects.isNull(houseDTO.getLatitude())) {
            house.setLatitude(houseDTO.getLatitude());
        }
        if (!Objects.isNull(houseDTO.getLongitude())) {
            house.setLongitude(houseDTO.getLongitude());
        }
        if (!Objects.isNull(houseDTO.getName())) {
            house.setName(houseDTO.getName());
        }

        houseRepository.save(house);

    }


}
