package hellospring.demo.repository;

import hellospring.demo.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    Member save(Member member);

    Optional<Member> findById(long id);

    Optional<Member> findByName(String name);

    List<Member> findAll();

    void updateMember(long id, String name);

    void deleteMember(long id);
}
