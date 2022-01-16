package db.course_work.backend.services;

import db.course_work.backend.entities.Member;
import db.course_work.backend.entities.Synagogue;
import db.course_work.backend.exceptions.UserNotFoundException;
import db.course_work.backend.repositories.MemberRepository;
import db.course_work.backend.repositories.SynagogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class SynagogueService {
    private final SynagogueRepository synagogueRepository;
    private final MemberRepository memberRepository;

    public SynagogueService(SynagogueRepository synagogueRepository, MemberRepository memberRepository) {
        this.synagogueRepository = synagogueRepository;
        this.memberRepository = memberRepository;
    }

    public Optional<Synagogue> getMemberSynagogue(long memberId) {
        return synagogueRepository.findSynagogueByMemberId(memberId);
    }

    public Member getMember(long memberId) {
        Optional<Member> memberOptional = memberRepository.findById(memberId);
        if (memberOptional.isEmpty()) throw new UserNotFoundException();
        return memberOptional.get();
    }

    @Transactional
    public Set<Member> getSynagogueMembers(long memberId) {
        Member member = getMember(memberId);
        return memberRepository.findMembersBySynagogueId(member.getSynagogue().getId());
    }

    public List<Synagogue> getAllSynagogues() {
        return synagogueRepository.findAll();
    }
}
