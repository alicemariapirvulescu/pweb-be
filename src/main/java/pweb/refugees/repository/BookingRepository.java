package pweb.refugees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pweb.refugees.domain.Booking;
import pweb.refugees.domain.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;


public interface BookingRepository extends JpaRepository<Booking,Long> {
    List<Booking> findAllByStartDateAndEndDate(LocalDate startDate, LocalDate endDate);
    Set<Booking> findAllByRentHouse_Owner(User owner);
    Set<Booking> findAllByGuest(User guest);
}
