package db.course_work.backend.controllers;

import db.course_work.backend.dto.mappers.MemberMapper;
import db.course_work.backend.dto.request.LoginRequest;
import db.course_work.backend.dto.request.MemberRequest;
import db.course_work.backend.dto.response.LoginResponse;
import db.course_work.backend.dto.response.MemberDto;
import db.course_work.backend.entities.Member;
import db.course_work.backend.repositories.MemberRepository;
import db.course_work.backend.services.MemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

// TODO: move all repository logic to service

@Slf4j
@Controller
public class AuthController {
    private final MemberService memberService;
    private final MemberRepository memberRepository;
    private final MemberMapper memberMapper;

    @Autowired
    public AuthController(MemberService memberService, MemberRepository memberRepository, MemberMapper memberMapper) {
        this.memberService = memberService;
        this.memberRepository = memberRepository;
        this.memberMapper = memberMapper;
    }

    @RequestMapping(value = "/register", consumes = "application/json", produces = "application/json", method = {RequestMethod.OPTIONS, RequestMethod.POST})
    public ResponseEntity<String> register(@Valid @RequestBody MemberRequest member, BindingResult result) {
        try {
            log.info("POST request to register user {}", member);
            if (result.hasErrors()) {
                log.info("Validation Error");
                return new ResponseEntity<>("Validation Error", HttpStatus.BAD_REQUEST);
            }

            boolean isSaved = memberService.saveMember(member);
            System.out.println(isSaved);
            return isSaved ? new ResponseEntity<>("User registered successfully!", HttpStatus.OK) :
                    new ResponseEntity<>("User has already registered!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            log.info("Unexpected Error {}", e.getMessage());
            return new ResponseEntity<>("Validation Error", HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/login", consumes = "application/json", method = {RequestMethod.OPTIONS, RequestMethod.POST})
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest, BindingResult bindingResult) {
        try {
            log.debug("POST request to login user {}", loginRequest);

            if(bindingResult.hasErrors()) {
                log.error("Validation error");
                return new ResponseEntity<>("Ошибка валидации", HttpStatus.BAD_REQUEST);
            }

            Member member = memberRepository.findMemberByLogin(loginRequest.getLogin());
            MemberDto memberDto = memberMapper.convertMemberToDto(member);
            LoginResponse loginResponse = new LoginResponse(memberService.getUserToken(loginRequest), memberDto);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Unexpected error {}", e.getMessage());
            return new ResponseEntity<>("Неверные учетные данные пользователя", HttpStatus.BAD_REQUEST);
        }
    }
}
