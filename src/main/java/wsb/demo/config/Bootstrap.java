package wsb.demo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import wsb.demo.auth.Authority;
import wsb.demo.auth.AuthorityName;
import wsb.demo.auth.AuthorityRepository;
import wsb.demo.auth.PersonService;

@Service
public class Bootstrap implements InitializingBean {

    private final AuthorityRepository authorityRepository;
    private final PersonService personService;


    public Bootstrap(AuthorityRepository authorityRepository, PersonService personService) {
        this.authorityRepository = authorityRepository;
        this.personService = personService;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("Rozpoczynamy przygotowywanie aplikacji...");

        prepareAuthoritues();

        personService.prepareAdminUser();
    }

    private void prepareAdmin() {
    }


    private void prepareAuthoritues() {

        for(AuthorityName authorityName:AuthorityName.values()){

            Authority existingAuthority = authorityRepository.findByName(authorityName);
            if (existingAuthority != null){
                System.out.println("Uprawnienie " + authorityName.name() + " już istnieje");
                continue;
            }
            System.out.println("Zapisujemy nowe uprawnienia  " + authorityName.name());

            Authority  newAuthority = new Authority(authorityName);
            authorityRepository.save(newAuthority);
        }

    }

}