package db.course_work.backend.services;

import db.course_work.backend.entities.Synagogue;
import db.course_work.backend.repositories.SynagogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SynagogueService {
    private final SynagogueRepository synagogueRepository;

    public SynagogueService(SynagogueRepository synagogueRepository) {
        this.synagogueRepository = synagogueRepository;
    }

    public Optional<Synagogue> getMemberSynagogue(long memberId) {
        return synagogueRepository.findSynagogueByMemberId(memberId);
    }


}
