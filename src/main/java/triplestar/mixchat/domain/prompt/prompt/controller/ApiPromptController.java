package triplestar.mixchat.domain.prompt.prompt.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptReq;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptDetailResp;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptListResp;
import triplestar.mixchat.global.response.ApiResponse;
import triplestar.mixchat.global.springdoc.CommonBadResponse;
import triplestar.mixchat.global.springdoc.SuccessResponse;

@Tag(name = "ApiPromptController", description = "프롬프트 관리 API")
@CommonBadResponse
@SuccessResponse
public interface ApiPromptController {

    // --- 1. 프롬프트 생성 (POST /create) ---
    @Operation(
            summary = "프롬프트 생성",
            description = "새로운 커스텀 프롬프트를 DB에 저장합니다. (PREMIUM 회원만 가능)"
    )
    ApiResponse<PromptDetailResp> create(
            @RequestBody(description = "프롬프트 생성 정보", required = true)
            PromptReq promptReq
    );

    // --- 2. 프롬프트 수정 (PUT /update/{id}) ---
    @Operation(
            summary = "프롬프트 수정",
            description = "본인이 생성한 커스텀 프롬프트를 수정합니다. (PREMIUM 회원만 가능)"
    )
    ApiResponse<PromptDetailResp> update(
            Long id,
            @RequestBody(description = "프롬프트 수정 정보", required = true)
            PromptReq promptReq
    );

    // --- 3. 프롬프트 삭제 (DELETE /delete/{id}) ---
    @Operation(
            summary = "프롬프트 삭제",
            description = "본인이 생성한 커스텀 프롬프트를 삭제합니다. (PREMIUM 회원만 가능)"
    )
    ApiResponse<Void> delete(Long id);

    // --- 4. 프롬프트 목록 조회 (GET /) ---
    @Operation(
            summary = "프롬프트 목록 조회",
            description = "사용 가능한 프롬프트 목록을 id와 제목만 조회합니다. (회원 등급별로 다름)"
    )
    ApiResponse<PromptListResp> list();

    // --- 5. 프롬프트 상세 조회 (GET /{id}) ---
    @Operation(
            summary = "프롬프트 상세 조회",
            description = "프리미엄 회원만 자신이 작성한 커스텀 프롬프트 상세정보를 조회할 수 있습니다."
    )
    ApiResponse<PromptDetailResp> detail(Long id);
}

