package triplestar.mixchat.domain.prompt.prompt.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import triplestar.mixchat.domain.prompt.prompt.dto.*;
import triplestar.mixchat.domain.prompt.prompt.service.PromptService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/prompt")
@RequiredArgsConstructor
public class ApiV1PromptController {
    private final PromptService promptService;

    @PostMapping("/create")
    public ResponseEntity<Long> create(@RequestBody PromptCreateReq req) {
        Long id = promptService.create(req);
        return ResponseEntity.ok(id);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @RequestBody PromptUpdateReq req, @RequestParam Long memberId) {
        promptService.update(id, req, memberId);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id, @RequestParam Long memberId) {
        promptService.delete(id, memberId);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/list")
    public ResponseEntity<List<PromptListResp>> list() {
        List<PromptListResp> list = promptService.list();
        return ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PromptDetailResp> detail(@PathVariable Long id) {
        PromptDetailResp resp = promptService.detail(id);
        return ResponseEntity.ok(resp);
    }
}

