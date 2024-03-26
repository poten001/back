package com.example.TCC.controller;

import com.example.TCC.domain.Member;
import com.example.TCC.dto.request.DrawChallengeRequestDto;
import com.example.TCC.dto.response.CompleteChallengeResponseDto;
import com.example.TCC.dto.response.DrawChallengeResponseDto;
import com.example.TCC.dto.response.TryChallengeResponseDto;
import com.example.TCC.service.ChallengeService;
import com.example.TCC.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/challenge")
public class ChallengeController {

    private final MemberService memberService;
    private final ChallengeService challengeService;

    //챌린지 뽑기
    @PostMapping("/draw")
    public ResponseEntity<DrawChallengeResponseDto> draw(@RequestBody DrawChallengeRequestDto dto, @RequestHeader("Authorization") String authorizationHeader) {
        //로그인한 사용자가 있는지 확인
        Member member = memberService.getCurrentUser(authorizationHeader);

        DrawChallengeResponseDto drawDto = challengeService.draw(dto, member);
        return ResponseEntity.status(HttpStatus.OK).body(drawDto);
    }

    //챌린지 도전
     @PostMapping("/try")
     public ResponseEntity<?> tryChallenge(@RequestHeader("Authorization") String authorizationHeader) {
         //로그인한 사용자가 있는지 확인
         Member member = memberService.getCurrentUser(authorizationHeader);

         challengeService.tryChallenge(member);
         return ResponseEntity.status(HttpStatus.OK).body("챌린지에 도전하셨습니다.");
    }

    //챌린지 도전항목 조회 (오챌완 버튼도 이걸로)
    @GetMapping("/try/check")
    public ResponseEntity<TryChallengeResponseDto> tryCheck(@RequestHeader("Authorization") String authorizationHeader) {
        //로그인한 사용자가 있는지 확인
        Member member = memberService.getCurrentUser(authorizationHeader);

        TryChallengeResponseDto tryDto = challengeService.tryCheck(member);
        return ResponseEntity.status(HttpStatus.OK).body(tryDto);
    }

    //챌린지 완료
    @PostMapping("/complete")
    public ResponseEntity<CompleteChallengeResponseDto> complete(@RequestHeader("Authorization") String authorizationHeader) {
        //로그인한 사용자가 있는지 확인
        Member member = memberService.getCurrentUser(authorizationHeader);

        CompleteChallengeResponseDto completeDto = challengeService.complete(member);
        return ResponseEntity.status(HttpStatus.OK).body(completeDto);
    }

    //완료한 챌린지 전체 조회

    //완료한 챌린지 상세 조회
}
