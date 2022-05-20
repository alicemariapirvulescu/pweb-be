package pweb.refugees.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "houses")
@Setter
@Getter
public class House {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "city")
    private String city;

    @Column(name = "phone")
    private String phone;

    @Column(name = "address")
    private String address;

    @Column(name = "latitude")
    private Double latitude;

    @Column(name = "longitude")
    private Double longitude;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "booking_period")
    private Integer bookingPeriod;

    @Lob
    @Column(name = "image")
    private String image;

    @Setter
    @OneToOne(targetEntity = User.class, cascade=CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id",name = "owner_id")
    private User owner;

    public House(String description, String city, Integer capacity, Double latitude, Double longitude,
                 String phone, Integer bookingPeriod,String image,String address, String name) {
        this.description = description;
        this.city = city;
        this.capacity = capacity;
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.bookingPeriod = bookingPeriod;
        this.image = image;
        this.name = name;
        this.address = address;
    }

    public House() {

    }
}
