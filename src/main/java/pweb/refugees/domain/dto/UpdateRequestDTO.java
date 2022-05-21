package pweb.refugees.domain.dto;

import lombok.Getter;
import pweb.refugees.enums.BookingStatus;

@Getter
public class UpdateRequestDTO {
    Long bookingId;
    BookingStatus bookingStatus;
}
