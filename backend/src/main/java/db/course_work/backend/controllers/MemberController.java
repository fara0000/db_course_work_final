package db.course_work.backend.controllers;

import db.course_work.backend.services.MemberService;
import db.course_work.backend.utils.MemberDTO;
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

@Slf4j
@Controller
public class MemberController {
    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    @RequestMapping(value = "/register", consumes = "application/json", produces = "application/json", method = {RequestMethod.OPTIONS, RequestMethod.POST})
    public ResponseEntity<String> register(@Valid @RequestBody MemberDTO member, BindingResult result) {
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
}
