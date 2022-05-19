package pweb.refugees.domain.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class BookingHolderDto {

    Set<BookingDTO> approvedNotifications;
    Set<BookingDTO> pendingNotifications;
}
