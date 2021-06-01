package wsb.demo.issues;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.DisplayNameGenerator;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import wsb.demo.auth.Person;
import wsb.demo.auth.PersonRepository;

import java.util.ArrayList;
import java.util.List;


import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@DisplayNameGeneration(DisplayNameGenerator.ReplaceUnderscores.class)
@ExtendWith(MockitoExtension.class)
class IssueControllerTest {

    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private IssueController issueController;

    private MockMvc mockMvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(issueController).build();
    }

    @Test
    public void testList() throws Exception {
        List<Person> person = new ArrayList<>();
        person.add(new Person());
        person.add(new Person());

        when(personRepository.findAll()).thenReturn((List) person);


        mockMvc.perform(get("issue/"))
                .andExpect(status().isOk())
                .andExpect(view().name("issue/index"))
                .andExpect(model().attribute("people",hasSize(2)));
    }

}