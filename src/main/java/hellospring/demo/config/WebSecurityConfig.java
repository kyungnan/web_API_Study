package hellospring.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()            //@EnableWebSecurity 사용으로 스프링보안이 활성화되었는데 그것을 비활성화해줌. csfr.disable() csrf 비활성화
                .authorizeRequests()    //접근 관리를 시작한다는 의미
                .antMatchers("/**").permitAll();
    }
}

