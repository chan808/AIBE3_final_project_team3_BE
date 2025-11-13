package triplestar.mixchat.domain.prompt.prompt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptReq;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptListResp;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptDetailResp;
import triplestar.mixchat.domain.prompt.prompt.service.PromptService;
import triplestar.mixchat.global.response.ApiResponse;
import triplestar.mixchat.global.security.CustomUserDetails;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prompt")
@RequiredArgsConstructor
public class ApiV1PromptController implements ApiPromptController{
    private final PromptService promptService;

    @PostMapping("/create")
    public ApiResponse<Void> create(@AuthenticationPrincipal CustomUserDetails userDetails,
                                    @RequestBody PromptReq req) {
        Long memberId = userDetails.getId();
        promptService.create(memberId, req);
        return ApiResponse.ok("프롬프트가 생성되었습니다.");
    }

    @PutMapping("/{id}")
    public ApiResponse<Void> update(@AuthenticationPrincipal CustomUserDetails userDetails,
                                    @PathVariable Long id,
                                    @RequestBody PromptReq req) {
        Long memberId = userDetails.getId();
        promptService.update(memberId, id, req);
        return ApiResponse.ok("프롬프트가 수정되었습니다.");
    }

    @DeleteMapping("/{id}")
    public ApiResponse<Void> delete(@AuthenticationPrincipal CustomUserDetails userDetails,
                                    @PathVariable Long id) {
        Long memberId = userDetails.getId();
        promptService.delete(memberId, id);
        return ApiResponse.ok("프롬프트가 삭제되었습니다.");
    }

    @GetMapping()
    public ApiResponse<List<PromptListResp>> list(@AuthenticationPrincipal CustomUserDetails userDetails) {
        Long memberId = userDetails.getId();
        List<PromptListResp> resp = promptService.list(memberId);
        return ApiResponse.ok("프롬프트 목록 조회 성공", resp);
    }

    @GetMapping("/{id}")
    public ApiResponse<PromptDetailResp> detail(@AuthenticationPrincipal CustomUserDetails userDetails,
                                                @PathVariable Long id) {
        Long memberId = userDetails.getId();
        PromptDetailResp resp = promptService.detail(memberId, id);
        return ApiResponse.ok("프롬프트 상세 조회 성공", resp);
    }
}
