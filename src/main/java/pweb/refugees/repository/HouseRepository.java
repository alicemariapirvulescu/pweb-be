package pweb.refugees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pweb.refugees.domain.House;
import pweb.refugees.domain.User;
import pweb.refugees.domain.dto.HouseDTO;

import java.util.List;
import java.util.Set;

@Repository
public interface HouseRepository extends JpaRepository<House, Long> {

    List<House> findAllByOwner(User user);

    @Query(value = "select * from houses where booking_period >= :numDays and capacity >= :numPeople and city= :city",
            nativeQuery = true)
    Set<House> findAllHousesFiltered(String city, Integer numPeople, Integer numDays);
}
