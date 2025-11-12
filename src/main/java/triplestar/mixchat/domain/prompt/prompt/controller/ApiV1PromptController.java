package triplestar.mixchat.domain.prompt.prompt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptReq;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptListResp;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptDetailResp;
import triplestar.mixchat.domain.prompt.prompt.service.PromptService;
import java.util.List;

@RestController
@RequestMapping("/api/v1/prompt")
@RequiredArgsConstructor
public class ApiV1PromptController {
    private final PromptService promptService;

    @PostMapping("/create")
    public ResponseEntity<PromptDetailResp> create(@RequestBody PromptReq req) {
        return ResponseEntity.ok(promptService.create(req));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<PromptDetailResp> update(@PathVariable Long id, @RequestBody PromptReq req) {
        return ResponseEntity.ok(promptService.update(id, req));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        promptService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<PromptListResp>> list() {
        return ResponseEntity.ok(promptService.list());
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromptDetailResp> detail(@PathVariable Long id) {
        return ResponseEntity.ok(promptService.detail(id));
    }
}
