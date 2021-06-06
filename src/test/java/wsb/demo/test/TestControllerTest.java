package wsb.demo.test;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import wsb.demo.auth.CustomUserDetailsService;
import wsb.demo.auth.Person;
import wsb.demo.auth.PersonService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(TestController.class)
@ExtendWith(MockitoExtension.class)
class TestControllerTest {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private PersonService personService;
    @MockBean
    private CustomUserDetailsService customUserDetailsService;
    @Test
    void test_ok() throws Exception {
        var person = new Person();
        person.setUsername("JANUSZ");
        var optionalPerson = Optional.of(person);

        when(personService.findPersonById(any(Long.class))).thenReturn(optionalPerson);

        this.mockMvc.perform(get("/test"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username")
                        .value("JANUSZ"));
    }
}