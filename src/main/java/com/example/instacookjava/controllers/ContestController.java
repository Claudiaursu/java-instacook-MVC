package com.example.instacookjava.controllers;
import java.util.*;

import com.example.instacookjava.models.Contest;
import com.example.instacookjava.services.ContestService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/contests")
public class ContestController {

    private final ContestService contestService;

    public ContestController(ContestService contestService) {
        this.contestService = contestService;
    }

    @GetMapping
    public ResponseEntity<List<Contest>> getAllContests() {
        return ResponseEntity.ok().body(contestService.getAllContests());
    }

    @PostMapping
    public ResponseEntity<Contest> createContest(@RequestBody Contest contest,  @RequestParam int kitchenId) {
        return ResponseEntity.ok().body(contestService.createContest(contest, kitchenId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Contest> getContestById(@PathVariable("id") Integer id) {
        return ResponseEntity.ok().body(contestService.getContestById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contest> updateContest(@PathVariable("id") Integer id, @RequestBody Contest contest) {
        return ResponseEntity.ok().body(contestService.updateContest(id, contest));
    }

    @DeleteMapping("/{id}")
    public void deleteContest(@PathVariable("id") Integer id) {
        contestService.deleteContest(id);
    }
}
