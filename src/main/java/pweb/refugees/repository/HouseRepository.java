package pweb.refugees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pweb.refugees.domain.House;

import java.util.Set;

@Repository
public interface HouseRepository extends JpaRepository<House,Long> {

}
