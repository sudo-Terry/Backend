package team3.kummit.service;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import team3.kummit.domain.Member;
import team3.kummit.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member findById(Long memberId){
        return memberRepository.findById(memberId).orElseThrow(()-> new IllegalArgumentException("Not existing Member id"));
    }

    public Member findByName(String name){
        return memberRepository.findByName(name);
    }


}
