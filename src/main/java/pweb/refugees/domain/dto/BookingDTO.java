package pweb.refugees.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pweb.refugees.enums.BookingStatus;
import java.time.LocalDate;

@Getter
@Setter
public class BookingDTO {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private String guestName;

    private BookingStatus bookingStatus;

    private String houseName;

    private String guestPhone;

    private String ownerPhone;

    private String ownerName;

    public BookingDTO(Long id, LocalDate startDate, LocalDate endDate, String guestName, BookingStatus bookingStatus, String houseName,String ownerName, String ownerPhone,String guestPhone) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.guestName = guestName;
        this.bookingStatus = bookingStatus;
        this.houseName = houseName;
        this.ownerName = ownerName;
        this.ownerPhone = ownerPhone;
        this.guestPhone = guestPhone;
    }
}
