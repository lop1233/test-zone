package test.controller.response;

import lombok.Builder;
import lombok.Getter;
import test.domain.Guest;
import test.domain.Role;
import test.domain.Status;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Builder
public class GuestResponse {

    private final Long id;
    private final String name;
    private final int age;
    private final Status status;
    private final Role role;
    private final ZonedDateTime createdAt;

    public static GuestResponse fromDomain(Guest guest) {
        if (Objects.isNull(guest)) {
            return null;
        }
        return GuestResponse.builder()
                .id(guest.getId())
                .name(guest.getName())
                .age(guest.getAge())
                .status(guest.getStatus())
                .role(guest.getRole())
                .createdAt(guest.getCreatedAt())
                .build();
    }
}
