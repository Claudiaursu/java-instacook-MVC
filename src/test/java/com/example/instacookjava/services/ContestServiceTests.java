package com.example.instacookjava.services;
import java.sql.Date;
import java.util.*;
import static org.mockito.Mockito.verify;

import com.example.instacookjava.models.*;
import com.example.instacookjava.repositories.ContestRepository;
import com.example.instacookjava.repositories.KitchenRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ContestServiceTests {
    @InjectMocks
    private ContestService contestService;

    @Mock
    private ContestRepository contestRepository;

    @Mock
    private KitchenRepository kitchenRepository;

    @Test
    @DisplayName("Get all contests happy flow")
    void getAllContests() {
        //arrange
        Contest contest1 = new Contest("Best Deserts", new Date(2022, 3, 2), new Date(2022, 3, 6), true, 100);
        Contest contest2 = new Contest("Best Deserts2", new Date(2022, 3, 2), new Date(2022, 3, 6), true, 100);

        List<Contest> contestList = new ArrayList<>();
        contestList.add(contest1);
        contestList.add(contest2);

        when(contestRepository.findAll()).thenReturn(contestList);

        //check
        List<Contest> result = contestService.getAllContests();

        //assert
        assertEquals(contestList.size(), result.size());
    }

    @Test
    void getContestByIdHappyFlow() {
        //arrange
        int contestId = 1;
        Contest contest = new Contest("Best Deserts", new Date(2022, 3, 2), new Date(2022, 3, 6), true, 100);
        when(contestRepository.findById(contestId)).thenReturn(Optional.of(contest));

        //check
        Contest result = contestService.getContestById(contestId);

        //assert
        assertEquals(contest.getContestTitle(), result.getContestTitle());
    }

    @Test
    void createContest() {
        //arrange
        Contest contest = new Contest("Best Deserts", new Date(2022, 3, 2), new Date(2022, 3, 6), true, 100);
        when(contestRepository.save(contest)).thenReturn(contest);

        int kitchenId = 1;
        Kitchen kitchen = new Kitchen("Mexican Kitchen", "Tacos and Tacos", "Mexic");
        when(kitchenRepository.findById(kitchenId)).thenReturn(Optional.of(kitchen));

        //check
        Contest result = contestService.createContest(contest, kitchenId);

        //assert
        assertEquals(contest.getContestTitle(), result.getContestTitle());

    }

    @Test
    void updateContest() {
        //arrange
        int contestID = 1;
        Contest contest = new Contest("Best Deserts", new Date(2022, 3, 2), new Date(2022, 3, 6), true, 100);
        when(contestRepository.save(contest)).thenReturn(contest);

        //check
        Contest result = contestService.updateContest(contestID, contest);

        //assert
        assertEquals(result.getContestId(), contest.getContestId());

    }

    @Test
    void deleteRecipe() {
        //check
        contestService.deleteContest(1);
        //assert
        verify(contestRepository).deleteById(1);
    }

}