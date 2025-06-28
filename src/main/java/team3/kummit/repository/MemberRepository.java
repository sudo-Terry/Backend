package team3.kummit.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import team3.kummit.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    Optional<Member> findByName(String name);
}
