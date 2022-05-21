package pweb.refugees.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import pweb.refugees.enums.BookingStatus;

import javax.persistence.Column;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class BookingDTO {

    private Long id;

    private LocalDate startDate;

    private LocalDate endDate;

    private BookingStatus bookingStatus;

    private String houseName;

    private String image;

    private String ownerPhone;

    private String ownerName;

    private String guestName;

    private String guestPhone;

    private String guestMessage;

    private String guestNo;

    private String address;

}
