package triplestar.mixchat.domain.prompt.prompt.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "프롬프트 생성 요청 DTO")
public record PromptCreateReq(
    @NotBlank
    @Size(max = 255)
    @Schema(description = "프롬프트 제목", example = "예시 제목")
    String title,

    @NotBlank
    @Schema(description = "프롬프트 내용", example = "예시 내용")
    String content
) {}
