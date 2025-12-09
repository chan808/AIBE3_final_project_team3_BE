package triplestar.mixchat.domain.member.member.policy;

import triplestar.mixchat.domain.member.member.constant.Role;
import triplestar.mixchat.domain.member.member.entity.Member;

public class MemberAccessPolicy {

    public static boolean isNotAccessible(Member member) {
        return member.isBlocked()
                || !member.isDeleted()
                || Role.isNotMember(member.getRole());
    }
}
