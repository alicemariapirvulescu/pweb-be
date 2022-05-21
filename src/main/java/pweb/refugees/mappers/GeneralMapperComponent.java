package pweb.refugees.mappers;

import org.springframework.stereotype.Component;
import pweb.refugees.domain.Booking;
import pweb.refugees.domain.House;
import pweb.refugees.domain.User;
import pweb.refugees.domain.dto.BookingDTO;
import pweb.refugees.domain.dto.HouseDTO;
import pweb.refugees.domain.dto.UserDto;

@Component
public class GeneralMapperComponent {

    public House dtoToHouse(HouseDTO dto) {
        return new House(dto.getDescription(), dto.getCity(), dto.getCapacity(), dto.getLatitude(),
                dto.getLongitude(), dto.getPhone(), dto.getBookingPeriod(), dto.getImage(), dto.getAddress(),
                dto.getName());
    }

    public HouseDTO houseToDto(House entity) {
        return new HouseDTO(entity.getId(), entity.getAddress(), entity.getName(), entity.getDescription(),
                entity.getCity(), entity.getPhone(), entity.getLatitude(), entity.getLongitude(), entity.getCapacity(),
                entity.getBookingPeriod(), entity.getImage());
    }

    public BookingDTO bookingToDto(Booking entity) {
        return new BookingDTO(entity.getId(), entity.getStartDate(), entity.getEndDate(), entity.getBookingStatus(),
                entity.getRentHouse().getName(), entity.getRentHouse().getImage(), entity.getRentHouse().getPhone(),
                entity.getRentHouse().getOwner().getUsername(), entity.getGuest().getUsername(), entity.getGuestPhone(),
                entity.getGuestMessage(), entity.getGuestNo(), entity.getRentHouse().getAddress());
    }

    public UserDto userToDto(User entity) {
        return new UserDto(entity.getUsername(), entity.getEmail(), entity.getRole(), entity.getImageUrl());
    }
}
