package team3.kummit.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;


@Tag(name = "User-Query", description = "유저 정보 조회")
@RestController
public class MockController {

    @Operation(summary = "유저 정보", description = "특정 유저 조회")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "성공", content = @Content(mediaType = "application/json")),
            @ApiResponse(responseCode = "400", description = "잘못된 요청 (입력 데이터 오류 등)"),
            @ApiResponse(responseCode = "500", description = "서버 오류")
    })
    @GetMapping("/users/{id}")
    public ResponseEntity<String> getUserInfo(@Parameter(description = "유저의 ID") @PathVariable Long id) {
        return ResponseEntity.ok(""+id);
    }

}
