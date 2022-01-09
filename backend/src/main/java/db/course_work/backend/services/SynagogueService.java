package db.course_work.backend.services;

import db.course_work.backend.entities.Synagogue;
import db.course_work.backend.repositories.SynagogueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SynagogueService {
    private final SynagogueRepository synagogueRepo;
    @Autowired
    SynagogueService(SynagogueRepository synagogueRepo) {
        this.synagogueRepo = synagogueRepo;
    }

    public List<Synagogue> getAllSynagogues() {
        return this.synagogueRepo.findAll();
    }
}
