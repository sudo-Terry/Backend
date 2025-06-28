package team3.kummit.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

import lombok.RequiredArgsConstructor;
import team3.kummit.dto.EmotionBandDetailResponse;
import team3.kummit.dto.EmotionBandListResponse;
import team3.kummit.service.EmotionBandService;

@Tag(name = "EmotionBand", description = "감정밴드 관리")
@RequestMapping("/api/emotion-bands")
@RestController
@RequiredArgsConstructor
public class EmotionBandController {

    private final EmotionBandService emotionBandService;

    @Operation(summary = "감정밴드 목록 조회", description = "인기 밴드(좋아요 수 기준 상위 3개)와 전체 밴드(최신순 상위 10개)를 조회합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping
    public ResponseEntity<EmotionBandListResponse> getEmotionBandLists(
            @Parameter(description = "사용자 ID (선택사항)") @RequestParam(required = false) Long memberId) {
        EmotionBandListResponse response = emotionBandService.getEmotionBandLists(memberId);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "감정밴드 상세 조회", description = "감정밴드의 상세 정보, 음악 목록, 댓글 목록을 조회합니다")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "404", description = "감정밴드를 찾을 수 없음"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/{emotionBandId}/detail")
    public ResponseEntity<EmotionBandDetailResponse> getEmotionBandDetail(
            @Parameter(description = "감정밴드 ID") @PathVariable Long emotionBandId) {
        EmotionBandDetailResponse response = emotionBandService.getEmotionBandDetail(emotionBandId);
        return ResponseEntity.ok(response);
    }
}
