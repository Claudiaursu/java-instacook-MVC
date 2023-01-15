package com.example.instacookjava.services;
import java.util.*;

import com.example.instacookjava.models.Contest;
import com.example.instacookjava.repositories.ContestRepository;
import org.springframework.stereotype.Service;

@Service
public class ContestService {

    private ContestRepository contestRepository;

    public ContestService(ContestRepository contestRepository) {
        this.contestRepository = contestRepository;
    }

    public List<Contest> getAllContests() {
        return contestRepository.findAll();
    }

    public Contest getContestById(Integer id) {
        return contestRepository.findById(id).orElse(null);
    }

    public Contest createContest(Contest contest) {
        return contestRepository.save(contest);
    }

    public Contest updateContest(Integer id, Contest contest) {
        contest.setContestId(id);
        return contestRepository.save(contest);
    }

    public void deleteContest(Integer id) {
        contestRepository.deleteById(id);
    }
}