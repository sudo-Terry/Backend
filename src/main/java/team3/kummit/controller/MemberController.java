package team3.kummit.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import team3.kummit.domain.Member;
import team3.kummit.dto.*;
import team3.kummit.service.MemberService;
import team3.kummit.service.music.MusicResponse;

import java.util.List;


@Slf4j
@Tag(name = "회원", description = "회원 API")
@RequestMapping("/api/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @Operation(
        summary = "회원 로그인",
        description = "이름을 가지고 요청을 합니다. 테스트 데이터로 박태희, 소일전 이렇게 넣어놨습니다"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그인 성공"),
        @ApiResponse(responseCode = "401", description = "로그인 실패: 인증되지 않음")
    })
    @PostMapping("/login")
    public ResponseEntity<MemberLoginResponse> memberLogin(
            @Parameter(description = "로그인 요청 데이터 (이메일과 비밀번호 포함)")
            @RequestBody MemberLoginRequest memberLoginRequest) {
        Member member = memberService.findByName(memberLoginRequest.name());
        if(member == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new MemberLoginResponse(member.getId()));
    }

    @GetMapping("/profile")
    public ResponseEntity<MemberProfileResponse> createEmotionBand(
            @Parameter(description = "사용자 ID (선택사항)") @RequestParam Long memberId) {
        Member member = memberService.findById(memberId);
        return ResponseEntity.status(HttpStatus.CREATED).body(new MemberProfileResponse(
                member.getName(),
                member.getSignUpDate(),
                member.getBandCreateCount() != null ? member.getBandCreateCount() : 0,
                member.getBandJoinCount() != null ? member.getBandJoinCount() : 0,
                member.getLikeCount() != null ? member.getLikeCount() : 0,
                member.getSongAddCount() != null ? member.getSongAddCount() : 0));
    }









}
