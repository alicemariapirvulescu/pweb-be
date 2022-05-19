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

    private String city;
    private String phone;
    private Integer capacity;


}
