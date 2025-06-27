package team3.kummit.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
@AllArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;
    private LocalDate signUpDate;
    private String email;
    private String password;

    private Integer bandCreateCount;
    private Integer bandJoinCount;
    private Integer likeCount;
    private Integer songAddCount;

}
