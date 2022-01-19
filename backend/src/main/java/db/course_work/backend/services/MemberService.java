package db.course_work.backend.services;

import db.course_work.backend.dto.request.LoginRequest;
import db.course_work.backend.entities.Member;
import db.course_work.backend.entities.Synagogue;
import db.course_work.backend.repositories.MemberRepository;
import db.course_work.backend.repositories.SynagogueRepository;
import db.course_work.backend.dto.request.MemberRequest;
import db.course_work.backend.security.JwtUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Slf4j
@AllArgsConstructor
@Service
public class MemberService implements UserDetailsService {
    private final JwtUtils jwtUtils;
    private final MemberRepository memberRepository;
    private final SynagogueRepository synagogueRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final AuthenticationManager authenticationManager;


    public boolean saveMember(MemberRequest memberRequest) {
        Member member = new Member();
        Synagogue synagogue = synagogueRepository.findById(memberRequest.getSynagogueId()).get();
        log.info(String.valueOf(synagogue));

        if(memberRepository.findMemberByLogin(member.getLogin()) != null) {
            log.info("1");
            log.info(String.valueOf(memberRepository.findMemberByLogin(member.getLogin()) != null));
            return false;
        }
        member.setName(memberRequest.getName());
        member.setSurname(memberRequest.getSurname());
        member.setLogin(memberRequest.getLogin());
        member.setPassword(memberRequest.getPassword());
        member.setRole(memberRequest.getRole());
        member.setSynagogue(synagogue);

        member.setPassword(bCryptPasswordEncoder.encode(member.getPassword()));
        memberRepository.save(member);

        return true;
    }

    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        Member member = memberRepository.findMemberByLogin(login);

        if(login == null) {
            throw new UsernameNotFoundException("Member not found");
        }

        return member;
    }

    public Member findMemberById(Long id) {
        Optional<Member> member = memberRepository.findById(id);
        return member.orElse(new Member());
    }

    public String getUserToken(@Valid LoginRequest member) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(member.getLogin(), member.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        System.out.println(authentication);
        return jwtUtils.generateJwtToken(authentication);
    }

    public List<Member> findAllMembers() {
        return memberRepository.findAll();
    }

    public boolean deleteUser(Long id) {
        if(memberRepository.findById(id).isPresent()) {
            memberRepository.deleteById(id);
            return true;
        }

        return false;
    }

}
