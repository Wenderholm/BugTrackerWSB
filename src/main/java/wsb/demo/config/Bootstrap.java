package wsb.demo.config;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class Bootstrap implements InitializingBean {

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("aplikacja jest uruchomiona");

    }

}