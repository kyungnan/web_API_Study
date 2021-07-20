package hellospring.demo.controller;
import hellospring.demo.domain.Criteria;
import hellospring.demo.domain.Member;
import hellospring.demo.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private MemberMapper memberMapper;
    @Autowired
    public MemberController(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    // 조회
    @GetMapping
    public List<Member> getAll(@RequestBody Criteria criteria) {
    //    return memberRepository.findAll();
        return memberMapper.getListWithPaging(criteria);
    }

    // 입력
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Member add(@RequestBody Member member) {
    //    memberRepository.save(member);
        memberMapper.insertMember(member);
        return member;
    }
     // id로 회원 조회
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Member> getById(@PathVariable long id){
   //     return memberRepository.findById(id);
        return memberMapper.getById(id);
    }

    // 수정
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Member member){
    //    memberRepository.updateMember(id, member.getName());
       memberMapper.updateMember(id, member.getName());
    }

    // 삭제
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id){
    //    memberRepository.deleteMember(id);
        memberMapper.deleteMember(id);
    }
}











