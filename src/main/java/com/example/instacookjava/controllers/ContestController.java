package com.example.instacookjava.controllers;
import java.util.*;

import com.example.instacookjava.models.Contest;
import com.example.instacookjava.services.ContestService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/contests")
public class ContestController {

    private final ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @GetMapping
    public List<Contest> getAllContests() {
        return contestService.getAllContests();
    }

    @PostMapping
    public Contest createContest(@RequestBody Contest contest) {
        return contestService.createContest(contest);
    }

    @GetMapping("/{id}")
    public Contest getContestById(@PathVariable("id") Integer id) {
        return contestService.getContestById(id);
    }

    @PutMapping("/{id}")
    public Contest updateContest(@PathVariable("id") Integer id, @RequestBody Contest contest) {
        return contestService.updateContest(id, contest);
    }

    @DeleteMapping("/{id}")
    public void deleteContest(@PathVariable("id") Integer id) {
        contestService.deleteContest(id);
    }
}
