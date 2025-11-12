package triplestar.mixchat.domain.prompt.prompt.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "프롬프트 목록 응답 DTO")
public record PromptListResp(
    @Schema(description = "프롬프트 ID", example = "1")
    Long id,

    @Schema(description = "프롬프트 제목", example = "예시 제목")
    String title
) {}
