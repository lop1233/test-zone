package test.service;

import org.junit.jupiter.api.Test;
import test.domain.Guest;
import test.domain.Role;
import test.domain.Status;
import test.gateway.GuestsGateway;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

class FindGuestTest {

    private final GuestsGateway guestsGateway = mock(GuestsGateway.class);
    private final FindGuest findGuest = new FindGuest(guestsGateway);

    @Test
    void shouldReturnAllGuestsWhenStatusIsNull() {
        List<Guest> guests = List.of(sampleGuest(Status.CONFIRMED));
        doReturn(guests).when(guestsGateway).findAll();

        List<Guest> result = findGuest.findByStatus(null);

        verify(guestsGateway).findAll();
        verifyNoMoreInteractions(guestsGateway);
        assertEquals(guests, result);
    }

    @Test
    void shouldReturnGuestsMatchingStatus() {
        List<Guest> notGoing = List.of(sampleGuest(Status.NOT_GOING));
        doReturn(notGoing).when(guestsGateway).findByStatus(Status.NOT_GOING);

        List<Guest> result = findGuest.findByStatus(Status.NOT_GOING);

        verify(guestsGateway).findByStatus(Status.NOT_GOING);
        verifyNoMoreInteractions(guestsGateway);
        assertEquals(notGoing, result);
    }

    @Test
    void shouldReturnConfirmedGuests() {
        List<Guest> confirmed = List.of(sampleGuest(Status.CONFIRMED));
        doReturn(confirmed).when(guestsGateway).findByStatus(Status.CONFIRMED);

        List<Guest> result = findGuest.findConfirmed();

        verify(guestsGateway).findByStatus(Status.CONFIRMED);
        verifyNoMoreInteractions(guestsGateway);
        assertEquals(confirmed, result);
    }

    private Guest sampleGuest(Status status) {
        return Guest.builder()
                .id(1L)
                .name("Bob")
                .age(25)
                .role(Role.ADULT)
                .status(status)
                .build();
    }
}
