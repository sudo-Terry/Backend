package team3.kummit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import team3.kummit.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {

    public Member findByName(String name);
}
