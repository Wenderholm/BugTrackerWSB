package wsb.demo.issues;

import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.http.ResponseEntity.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.junit.jupiter.api.Assertions.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class IssueControllerTest {
    @Autowired
    MockMvc mockMvc;

    @InjectMocks
    private IssueController issueController;

    @MockBean
    private IssueService issueService;

    @Test
    void index() throws Exception {
        List<Issue> listIssue = new ArrayList<>();
        listIssue.add(new Issue("aaa","bbb"));

        when(issueService.findAllIssue()).thenReturn(listIssue);
        this.mockMvc.perform(get("/issue/")).andExpect(status().isOk());
    }

    @Test
    void testIndex() {
    }
}