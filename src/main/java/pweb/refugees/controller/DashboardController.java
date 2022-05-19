package pweb.refugees.controller;

import com.sun.istack.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import pweb.refugees.domain.dto.BookingDTO;
import pweb.refugees.domain.dto.BookingHolderDto;
import pweb.refugees.domain.dto.BookingPeriodDTO;
import pweb.refugees.domain.dto.HouseDTO;
import pweb.refugees.service.DashboardService;

import java.util.Set;

@Slf4j
@RestController
@RequestMapping("api/dashboard")
public class DashboardController {


    @Autowired
    private DashboardService dashboardService;


    @PutMapping("/update-user/{role}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity updateRole(@PathVariable("role") @NotNull String role){
        log.info("Updating user");
       dashboardService.updateUserRole(role);
       log.info("Updated user");
        return ResponseEntity.ok().build();
    }

    @PutMapping("/update-status/{bookingId}/{status}")
    @PreAuthorize("hasRole('ROLE_HOST')")
    public ResponseEntity updateBookingStatus(@PathVariable("bookingId") @NotNull Long bookingId,
            @PathVariable("status") @NotNull String status){
        log.info("Updated booking");
        dashboardService.updateBooking(bookingId,status);
        log.info("Updated booking");
        return ResponseEntity.ok().build();
    }

    @PostMapping("/save-house")
    @PreAuthorize("hasRole('ROLE_HOST')")
    public ResponseEntity createHouse(@RequestBody HouseDTO houseView){
        log.info("Creating house");
        dashboardService.createHouse(houseView);
        log.info("Created house");
        return ResponseEntity.ok().build();

    }
    @PostMapping("/save-booking/{houseId}/")
    @PreAuthorize("hasRole('ROLE_GUEST')")
    public ResponseEntity createBooking(@PathVariable("houseId") Long houseId,
            @RequestBody BookingPeriodDTO periodDTO){
        log.info("Creating booking");
        dashboardService.createBooking(houseId,periodDTO);
        log.info("Created booking");
        return ResponseEntity.ok().build();
    }

    @GetMapping("/houses")
    @PreAuthorize("hasRole('ROLE_GUEST' or hasRole('ROLE_HOST'))")
    public ResponseEntity<Set<HouseDTO>> getHouses(){
        log.info("Getting houses");
        Set<HouseDTO> houses = dashboardService.getHouses();
        return ResponseEntity.ok().body(houses);
    }

    @GetMapping("/house/{id}")
    @PreAuthorize("hasRole('ROLE_GUEST' or hasRole('ROLE_HOST'))")
    public ResponseEntity<HouseDTO> getHouse(@PathVariable("id") Long id){
        log.info("Getting house");
        HouseDTO dto = dashboardService.getHouse(id);
        return ResponseEntity.ok().body(dto);
    }

    @GetMapping("/bookings")
    @PreAuthorize("hasRole('ROLE_HOST' or hasRole('ROLE_GUEST'))")
    public ResponseEntity<BookingHolderDto> getBookings(){
        log.info("Getting bookings");
        BookingHolderDto bookings = dashboardService.getBookings();
        return ResponseEntity.ok().body(bookings);
    }

    @GetMapping("/new")
    @PreAuthorize("hasRole('ROLE_HOST' or hasRole('ROLE_GUEST'))")
    public ResponseEntity<Boolean> chekcForNewBookings(){

        Boolean isNew= dashboardService.checkForNewBookings();
        return ResponseEntity.ok().body(isNew);
    }

    @GetMapping("/cities")
    @PreAuthorize("hasRole('ROLE_HOST' or hasRole('ROLE_GUEST'))")
    public ResponseEntity<Set<String>> getCities(){
        log.info("Getting cities");
        Set<String> cities= dashboardService.getCities();
        return ResponseEntity.ok().body(cities);
    }
    @PutMapping("/update-house")
    @PreAuthorize("hasRole('ROLE_HOST' or hasRole('ROLE_GUEST'))")
    public ResponseEntity updateHouse(@RequestBody HouseDTO house){
        log.info("Updating house");
        dashboardService.updateHouse(house);
        log.info("Updated house");
        return ResponseEntity.ok().build();
    }






}
