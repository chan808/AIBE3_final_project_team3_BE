package triplestar.mixchat.domain.prompt.prompt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import triplestar.mixchat.domain.prompt.prompt.dto.*;
import triplestar.mixchat.domain.prompt.prompt.entity.Prompt;
import triplestar.mixchat.domain.prompt.prompt.repository.PromptRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromptService {
    private final PromptRepository promptRepository;

    @Transactional
    public Long create(PromptCreateReq req) {
        Prompt prompt = new Prompt();
        prompt.setTitle(req.title());
        prompt.setContent(req.content());
        Prompt saved = promptRepository.save(prompt);
        return saved.getId();
    }

    @Transactional
    public void update(Long id, PromptUpdateReq req, Long memberId) {
        Prompt prompt = promptRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prompt not found"));
        // 권한 체크 필요시 memberId 비교
        prompt.setTitle(req.title());
        prompt.setContent(req.content());
        promptRepository.save(prompt);
    }

    @Transactional
    public void delete(Long id, Long memberId) {
        Prompt prompt = promptRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prompt not found"));
        // 권한 체크 필요시 memberId 비교
        promptRepository.delete(prompt);
    }

    @Transactional(readOnly = true)
    public List<PromptListResp> list() {
        return promptRepository.findAll().stream()
                .map(p -> new PromptListResp(p.getId(), p.getTitle()))
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PromptDetailResp detail(Long id) {
        Prompt prompt = promptRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prompt not found"));
        return new PromptDetailResp(prompt.getId(), prompt.getTitle(), prompt.getContent());
    }
}
