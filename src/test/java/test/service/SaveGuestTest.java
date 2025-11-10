package test.service;

import org.junit.jupiter.api.Test;
import test.domain.Guest;
import test.domain.Role;
import test.domain.Status;
import test.gateway.GuestsGateway;

import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class SaveGuestTest {

    private final GuestsGateway guestsGateway = mock(GuestsGateway.class);
    private final SaveGuest saveGuest = new SaveGuest(guestsGateway);

    @Test
    void shouldDelegateGuestPersistenceToGateway() {
        Guest toSave = Guest.builder()
                .name("Alice")
                .age(30)
                .role(Role.ADULT)
                .status(Status.CONFIRMED)
                .build();

        Guest persisted = Guest.builder()
                .id(1L)
                .name("Alice")
                .age(30)
                .role(Role.ADULT)
                .status(Status.CONFIRMED)
                .createdAt(ZonedDateTime.now())
                .build();

        doReturn(persisted).when(guestsGateway).save(toSave);

        Guest result = saveGuest.execute(toSave);

        verify(guestsGateway).save(toSave);
        assertSame(persisted, result);
        assertEquals("Alice", result.getName());
    }
}
