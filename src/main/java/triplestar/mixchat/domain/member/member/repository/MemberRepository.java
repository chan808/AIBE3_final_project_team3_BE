package triplestar.mixchat.domain.member.member.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import triplestar.mixchat.domain.member.member.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long> {
    boolean existsByEmail(String reqEmail);

    Optional<Member> findByEmail(String email);

    Optional<Member> findByNickname(String username);

    @Query("""
            SELECT m FROM Member m
            WHERE m.id <> :id AND m.isDeleted = false AND m.isBlocked = false AND m.role = 'ROLE_MEMBER'
    """)
    Page<Member> findAllByIdIsNot(Long id, Pageable pageable);

    @Query("""
            SELECT m FROM Member m
            WHERE m.id <> :currentUserId AND m.id IN :onlineMemberIds
                AND m.isDeleted = false AND m.isBlocked = false AND m.role = 'ROLE_MEMBER'
    """)
    Page<Member> findByIds(Long currentUserId, List<Long> onlineMemberIds, Pageable pageable);

    Page<Member> findAllByIdIn(List<Long> ids, Pageable pageable);

    @Modifying
    @Query("""
            UPDATE Member m
            SET m.lastSeenAt = :lastSeenAt
            WHERE m.id = :memberId
    """)
    void updateLastSeenAt(Long memberId, LocalDateTime lastSeenAt);
}
