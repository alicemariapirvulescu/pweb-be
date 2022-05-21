package pweb.refugees.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pweb.refugees.enums.BookingStatus;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "bookings")
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "guest_phone")
   private String guestPhone;

    @Column(name = "message")
    private String guestMessage;

    @Column(name = "guestNo")
    private String guestNo;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private BookingStatus bookingStatus;

    @OneToOne(targetEntity = House.class, cascade=CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id",name = "house_id")
    private House rentHouse;

    public Booking(LocalDate startDate, LocalDate endDate, User guest, String guestPhone, String guestMessage, String guestNo, BookingStatus bookingStatus, House rentHouse) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.guest = guest;
        this.guestPhone = guestPhone;
        this.guestMessage = guestMessage;
        this.guestNo = guestNo;
        this.bookingStatus = bookingStatus;
        this.rentHouse = rentHouse;
    }
}
