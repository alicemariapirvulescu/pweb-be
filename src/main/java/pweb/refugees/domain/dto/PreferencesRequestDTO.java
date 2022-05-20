package pweb.refugees.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PreferencesRequestDTO {

    private String city;
    private Integer numDays;
    private Integer numPeople;
}
