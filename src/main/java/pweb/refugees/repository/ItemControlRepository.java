package pweb.refugees.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pweb.refugees.domain.ItemControl;
import pweb.refugees.domain.User;

import java.util.Optional;

@Repository
public interface ItemControlRepository extends JpaRepository<ItemControl,Long> {
    Optional<ItemControl> findByOwner(User owner);
}
