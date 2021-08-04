package hellospring.demo.controller;
import hellospring.demo.domain.Criteria;
import hellospring.demo.domain.Member;
import hellospring.demo.mapper.MemberMapper;
import hellospring.demo.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/members")
public class MemberController {

    @Autowired
    private final MemberMapper memberMapper;
    private MemberService memberService;
    @Autowired
    public MemberController(MemberMapper memberMapper, MemberService memberService) {
        this.memberMapper = memberMapper;
        this.memberService = memberService;
    }

    // 조회
    @GetMapping
    public List<Member> getAll(Criteria criteria) {
        return memberMapper.getListWithPaging(criteria);
    }

    // 입력
    @PostMapping(consumes = "application/json")     //서버에서 이 핸들러 body에 담긴 데이터 타입을 APPLICATION_JSON_UTF8일 경우 요청을 처리한다는 의미
    @ResponseStatus(code = HttpStatus.CREATED)
    public Member add(@RequestBody Member member) throws NoSuchAlgorithmException, InvalidKeyException, BadPaddingException, NoSuchPaddingException, IllegalBlockSizeException {
        memberService.addMember(member);
        return member;
    }
     // id로 회원 조회
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Optional<Member> getById(@PathVariable long id){
        return memberMapper.getById(id);
    }

    // 수정
   @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public void update(@PathVariable long id, @RequestBody Member member) throws InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
    //   memberMapper.updateMember(id, member.getPassword(), member.getName());
       memberService.alterMember(id, member.getPassword(), member.getName());
    }

    // 삭제
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void remove(@PathVariable long id){
        memberMapper.deleteMember(id);
    }
}
