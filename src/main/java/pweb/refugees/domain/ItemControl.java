package pweb.refugees.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Table(name = "item_control")
public class ItemControl {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "current_bookings_count")
    private Long currentBookingsCount;

    @Column(name = "old_bookings_count")
    private Long oldBookingsCount;

    @Setter
    @OneToOne(targetEntity = User.class, cascade=CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id",name = "owner_id")
    private User owner;


}
