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
import team3.kummit.dto.MemberLoginRequest;
import team3.kummit.dto.MemberLoginResponse;
import team3.kummit.service.MemberService;


@Slf4j
@Tag(name = "회원", description = "회원 API")
@RequestMapping("/api/member")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/login")
    @Operation(
        summary = "회원 로그인",
        description = "이름을 가지고 요청을 합니다. 테스트 데이터로 박태희, 소일전 이렇게 넣어놨습니다"
    )
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "로그인 성공"),
        @ApiResponse(responseCode = "401", description = "로그인 실패: 인증되지 않음")
    })
    public ResponseEntity<MemberLoginResponse> memberLogin(
            @Parameter(description = "로그인 요청 데이터 (이메일과 비밀번호 포함)")
            @RequestBody MemberLoginRequest memberLoginRequest) {
        Member member = memberService.findByName(memberLoginRequest.name());
        if(member == null){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        return ResponseEntity.ok(new MemberLoginResponse(member.getId()));
    }
}
