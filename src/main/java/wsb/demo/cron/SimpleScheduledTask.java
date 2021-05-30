package wsb.demo.cron;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.scheduling.config.ScheduledTask;
import org.springframework.stereotype.Component;
import wsb.demo.auth.PersonRepository;

import java.text.SimpleDateFormat;
import java.util.Date;

@Component
public class SimpleScheduledTask {
    private final PersonRepository personRepository;
    private static final Logger log = LoggerFactory.getLogger(ScheduledTask.class);
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    public SimpleScheduledTask(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

//    @Scheduled(fixedRate = 2000)
//    public void executeTask() {
//        long number = personRepository.count();
//        log.info("All Users in application " + number);
//    }

}
