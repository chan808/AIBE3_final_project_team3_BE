package triplestar.mixchat.domain.member.member.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import triplestar.mixchat.domain.member.member.entity.Member;

public interface MemberRepositoryCustom {

    Page<Member> findAllByIdIsNot(Long id, Pageable pageable);

    @Query("""
            SELECT m FROM Member m
            WHERE m.id <> :currentUserId AND m.id IN :onlineMemberIds
                AND m.isDeleted = false AND m.isBlocked = false AND m.role = 'ROLE_MEMBER'
    """)
    Page<Member> findByIds(Long currentUserId, List<Long> onlineMemberIds, Pageable pageable);

    @Modifying
    @Query("""
            UPDATE Member m
            SET m.lastSeenAt = :lastSeenAt
            WHERE m.id = :memberId
    """)
    void updateLastSeenAt(Long memberId, LocalDateTime lastSeenAt);
}
