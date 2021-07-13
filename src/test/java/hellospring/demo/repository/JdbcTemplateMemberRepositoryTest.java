package hellospring.demo.repository;

import hellospring.demo.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import java.util.Optional;

@SpringBootTest
class JdbcTemplateMemberRepositoryTest {

    @Autowired MemberRepository memberRepository;

    @Test
    public void save() {
        //given
        Member member = new Member();
        member.setName("spring");
        //when
        memberRepository.save(member);
        Member result = memberRepository.findById(member.getId()).get();
        //then
        Assertions.assertThat(member).isEqualTo(result);
    }

}