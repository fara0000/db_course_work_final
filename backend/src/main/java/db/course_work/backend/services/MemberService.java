package db.course_work.backend.services;

import db.course_work.backend.entities.Member;
import db.course_work.backend.entities.Synagogue;
import db.course_work.backend.repositories.MemberRepository;
import db.course_work.backend.repositories.SynagogueRepository;
import db.course_work.backend.utils.MemberDTO;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final MemberRepository memberRepository;
    private final SynagogueRepository synagogueRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;


    public boolean saveMember(MemberDTO memberDTO) {
        Member member = new Member();
        Synagogue synagogue = synagogueRepository.findById(memberDTO.getSynagogueId()).get();
        log.info(String.valueOf(synagogue));

        if(memberRepository.findMemberByLogin(member.getLogin()) != null) {
            return false;
        }
        member.setName(memberDTO.getName());
        member.setSurname(memberDTO.getSurname());
        member.setLogin(memberDTO.getLogin());
        member.setPassword(memberDTO.getPassword());
        member.setRole(memberDTO.getRole());
        member.setSynagogue(synagogue);

        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        memberRepository.save(member);

        return true;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByLogin(login);

        if(login == null) {
            throw new UsernameNotFoundException("Member not found");
        }

        return member;
    }

    public Member findMemberById(Integer id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.orElse(new Member());
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public boolean deleteUser(Integer id) {
        if(memberRepository.findById(id).isPresent()) {
            memberRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
