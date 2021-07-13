package hellospring.demo.mapper;

import hellospring.demo.domain.Member;
import org.apache.ibatis.annotations.*;
import java.util.List;
import java.util.Optional;

@Mapper
public interface MemberMapper  {

    // @Param("id"): 파라미터로 들어오는 long id를 select 문의 where id=#{id}와 맵핑하기 위한 애노테이션
    @Select("select * from member where id=#{id}")
    Optional<Member> getById(@Param("id") long id);

    @Select("select * from member")
    List<Member> getAll();

    // sql문으로 결과가 변경된 레코드의 개수를 반환함 (int 형)
    @Insert("insert into member(name) values(#{name})")
    @Options(useGeneratedKeys = true, keyProperty = "id")
    int insertMember(Member member);

    @Update("update member set name=#{name} where id=#{id}")
    int updateMember(@Param("id") long id, @Param("name") String name);

    @Delete("delete from member where id=#{id}")
    int deleteMember(@Param("id") long id);
}
