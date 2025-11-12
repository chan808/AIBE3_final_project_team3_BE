package triplestar.mixchat.domain.prompt.prompt.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import triplestar.mixchat.domain.prompt.prompt.entity.Prompt;

public interface PromptRepository extends JpaRepository<Prompt, Long> {
}

