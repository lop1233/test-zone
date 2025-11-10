package test.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import test.domain.Guest;
import test.domain.Status;
import test.gateway.GuestsGateway;

import java.util.List;

@ApplicationScoped
public class FindGuest {

    private final GuestsGateway guestsGateway;

    @Inject
    public FindGuest(GuestsGateway guestsGateway) {
        this.guestsGateway = guestsGateway;
    }

    public List<Guest> findAll() {
        return guestsGateway.findAll();
    }

    public List<Guest> findByStatus(Status status) {
        if (status == null) {
            return findAll();
        }
        return guestsGateway.findByStatus(status);
    }

    public List<Guest> findConfirmed() {
        return guestsGateway.findByStatus(Status.CONFIRMED);
    }
}
