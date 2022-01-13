package db.course_work.backend.services;

import db.course_work.backend.entities.Member;
import db.course_work.backend.repositories.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;

public class MemberService {
    private final MemberRepository memberRepository;
    MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public boolean saveMember(Member member) {
        if(memberRepository.findMemberByLogin(member.getLogin()) != null) {
            log.debug();
            return false;
        }
    }
}
