package com.example.instacookjava.controllers;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import com.example.instacookjava.models.Contest;
import com.example.instacookjava.services.ContestService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import java.util.Arrays;
import java.util.List;
import java.sql.Date;

@ExtendWith(MockitoExtension.class)
class ContestControllerTests {

    @Mock
    private ContestService contestService;

    @InjectMocks
    private ContestController contestController;

    @Test
    void getAllContests() {
        Contest contest = new Contest("Best Deserts", new Date(2022, 3, 2), new Date(2022, 3, 6), true, 100);
        List<Contest> contests = Arrays.asList(contest);
        when(contestService.getAllContests()).thenReturn(contests);
        ResponseEntity<List<Contest>> response = contestController.getAllContests();
        assertEquals(contests, response.getBody());
        verify(contestService).getAllContests();
    }

    @Test
    void createContest() {
        Contest contest = new Contest("Best Deserts", new Date(2022, 3, 2), new Date(2022, 3, 6), true, 100);
        int kitchenId = 1;
        when(contestService.createContest(contest, kitchenId)).thenReturn(contest);
        ResponseEntity<Contest> response = contestController.createContest(contest, kitchenId);
        assertEquals(contest, response.getBody());
        verify(contestService).createContest(contest, kitchenId);
    }

    @Test
    void updateContest() {
        int id = 1;
        Contest contest = new Contest("Best Deserts", new Date(2022, 3, 2), new Date(2022, 3, 6), true, 100);
        when(contestService.updateContest(id, contest)).thenReturn(contest);
        ResponseEntity<Contest> response = contestController.updateContest(id, contest);
        assertEquals(contest, response.getBody());
        verify(contestService).updateContest(id, contest);
    }

    @Test
    void getContestById() {
        int id = 1;
        Contest contest = new Contest("Best Deserts", new Date(2022, 3, 2), new Date(2022, 3, 6), true, 100);
        when(contestService.getContestById(id)).thenReturn(contest);
        ResponseEntity<Contest> response = contestController.getContestById(id);
        assertEquals(contest, response.getBody());
        verify(contestService).getContestById(id);
    }

    @Test
    void deleteContest() {
        int id = 1;
        contestController.deleteContest(id);
        verify(contestService).deleteContest(id);
    }
}
