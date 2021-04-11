package wsb.demo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Service;
import wsb.demo.auth.Authority;
import wsb.demo.auth.AuthorityName;
import wsb.demo.auth.AuthorityRepository;

@Service
public class Bootstrap implements InitializingBean {

    private final AuthorityRepository authorityRepository;

    public Bootstrap(AuthorityRepository authorityRepository) {
        this.authorityRepository = authorityRepository;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
            prepareAuthoritues();
            prepareAdmin();
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