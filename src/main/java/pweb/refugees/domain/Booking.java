package pweb.refugees.domain;

import lombok.Getter;
import lombok.Setter;
import pweb.refugees.enums.BookingStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Entity
@Table(name = "bookings")
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;


    @Column(name = "start_date")
    private LocalDate startDate;

    @Column(name = "end_date")
    private LocalDate endDate;

    @OneToOne(targetEntity = User.class, cascade=CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id",name = "guest_id")
    private User guest;

    @Setter
    @Column(name = "guest_phone")
   private String guestPhone;

    @Setter
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToOne(targetEntity = House.class, cascade=CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id",name = "house_id")
    private House rentHouse;

    public Booking(LocalDate startDate, LocalDate endDate, User guest, BookingStatus bookingStatus, House rentHouse) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.guest = guest;
        this.bookingStatus = bookingStatus;
        this.rentHouse = rentHouse;
    }
}
