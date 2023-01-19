package com.example.instacookjava.services;
import java.util.*;

import com.example.instacookjava.models.Contest;
import com.example.instacookjava.models.Kitchen;
import com.example.instacookjava.repositories.ContestRepository;
import com.example.instacookjava.repositories.KitchenRepository;
import org.springframework.stereotype.Service;

@Service
public class ContestService {

    private ContestRepository contestRepository;
    private KitchenRepository kitchenRepository;

    public ContestService(
            ContestRepository contestRepository,
            KitchenRepository kitchenRepository
    ){
        this.contestRepository = contestRepository;
        this.kitchenRepository = kitchenRepository;
    }

    public List<Contest> getAllContests() {
        return contestRepository.findAll();
    }

    public Contest getContestById(Integer id) {
        return contestRepository.findById(id).orElse(null);
    }

    public Contest createContest(Contest contest, int kitchenId) {
        Kitchen kitchen = kitchenRepository
                .findById(kitchenId)
                .orElseThrow(() -> new RuntimeException("Could not find kitchen with id"));
        contest.setKitchen(kitchen);
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