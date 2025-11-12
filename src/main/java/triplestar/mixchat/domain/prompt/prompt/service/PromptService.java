package triplestar.mixchat.domain.prompt.prompt.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import triplestar.mixchat.domain.prompt.prompt.constant.PromptType;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptReq;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptListResp;
import triplestar.mixchat.domain.prompt.prompt.dto.PromptDetailResp;
import triplestar.mixchat.domain.prompt.prompt.entity.Prompt;
import triplestar.mixchat.domain.prompt.prompt.repository.PromptRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PromptService {
    private final PromptRepository promptRepository;

    @Transactional
    public PromptDetailResp create(PromptReq req) {
        Prompt prompt = Prompt.create(req.title(), req.content(), req.promptType());
        Prompt saved = promptRepository.save(prompt);
        return PromptDetailResp.of(saved);
    }

    @Transactional
    public PromptDetailResp update(Long id, PromptReq req) {
        Prompt prompt = promptRepository.findById(id).orElseThrow();
        prompt.modify(req.title(), req.content(), req.promptType());
        return PromptDetailResp.of(prompt);
    }

    @Transactional
    public void delete(Long id) {
        promptRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PromptListResp> list() {
        return promptRepository.findAll().stream()
                .map(PromptListResp::of)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PromptDetailResp detail(Long id) {
        Prompt prompt = promptRepository.findById(id).orElseThrow();
        return PromptDetailResp.of(prompt);
    }
}
