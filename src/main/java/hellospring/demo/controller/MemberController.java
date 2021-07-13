package hellospring.demo.controller;

import hellospring.demo.domain.Member;
import hellospring.demo.mapper.MemberMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    private MemberMapper mapper;

    public MemberController(MemberMapper mapper) {
        this.mapper = mapper;
    }

//    @Autowired
//    private JdbcTemplateMemberRepository memberRepository;

    // 조회
    @GetMapping
    public List<Member> getAll() {
    //    return memberRepository.findAll();
        return mapper.getAll();
    }

    // 입력
    @PostMapping
    @ResponseStatus(code = HttpStatus.CREATED)
    public Member add(@RequestBody Member member) {
//        memberRepository.save(member);
//        return member;
        mapper.insertMember(member);
        return member;
    }
     // id로 회원 조회
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Member> getById(@PathVariable long id){
   //     return memberRepository.findById(id);
        return mapper.getById(id);
    }

    // 수정
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Member member){
    //    memberRepository.updateMember(id, member.getName());
        mapper.updateMember(id, member.getName());
    }

    // 삭제
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id){
    //    memberRepository.deleteMember(id);
        mapper.deleteMember(id);
    }
}











