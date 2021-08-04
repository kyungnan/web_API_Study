package hellospring.demo.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Member {
    private long id;
    private String password;
    private String name;
    private String salt;

}
