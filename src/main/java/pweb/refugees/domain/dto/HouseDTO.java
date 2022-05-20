package pweb.refugees.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HouseDTO {

    private Long id;

    private String address;

    private String name;

    private String description;

    private String city;

    private String phone;

    private Double latitude;

    private Double longitude;

    private Integer capacity;

    private Integer bookingPeriod;

    private String image;



}
