package test.gateway;

import test.domain.Guest;
import test.domain.Status;

import java.util.List;

public interface GuestsGateway {
    Guest save(Guest guest);

    List<Guest> findAll();

    List<Guest> findByStatus(Status status);
}
