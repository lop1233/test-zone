package test.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import test.domain.Guest;
import test.gateway.GuestsGateway;

@ApplicationScoped
public class SaveGuest {

    private final GuestsGateway guestsGateway;

    @Inject
    public SaveGuest(GuestsGateway guestsGateway) {
        this.guestsGateway = guestsGateway;
    }

    public Guest execute(Guest guest) {
        return guestsGateway.save(guest);
    }
}
