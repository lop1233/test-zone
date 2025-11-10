package test.domain;

import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Guest {
    private Long id;
    private String name;
    private int age;
    private Status status;
    private ZonedDateTime createdAt;
    private Role role;

}
