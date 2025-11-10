package test.controller.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import test.domain.Guest;
import test.domain.Role;
import test.domain.Status;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class GuestRequest {

    @NotBlank
    private String name;

    @Min(0)
    private int age;

    @NotNull
    private Role role;

    private Status status;

    public Guest toDomain() {
        return Guest.builder()
                .name(name)
                .age(age)
                .role(role)
                .status(status != null ? status : Status.CONFIRMED)
                .build();
    }
}
