package pweb.refugees.domain.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class BookingPeriodDTO {

    private LocalDate startDate;
    private LocalDate endDate;
    private String phone;
    private String message;
    private String peopleNo;
    private Long houseId;

}
