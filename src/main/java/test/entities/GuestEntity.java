package test.entities;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import test.domain.Guest;
import test.domain.Role;
import test.domain.Status;

import java.time.ZonedDateTime;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "guests")
public class GuestEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "age")
    private int age;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "created_at")
    @CreationTimestamp
    private ZonedDateTime createdAt;

    public static GuestEntity fromDomain(Guest guest) {
        if (Objects.isNull(guest)) {
            return null;
        }
        return GuestEntity.builder()
                .id(guest.getId())
                .name(guest.getName())
                .age(guest.getAge())
                .status(guest.getStatus())
                .role(guest.getRole())
                .createdAt(guest.getCreatedAt())
                .build();
    }

    public Guest toDomain() {
        return Guest.builder()
                .id(this.id)
                .name(this.name)
                .age(this.age)
                .status(this.status)
                .role(this.role)
                .createdAt(this.createdAt)
                .build();
    }
}
