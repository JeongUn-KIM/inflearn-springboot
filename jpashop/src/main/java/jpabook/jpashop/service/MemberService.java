package jpabook.jpashop.service;

import jpabook.jpashop.domain.Member;
import jpabook.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true) //4 JPA의 모든 데이터 변경이나 로직들은 Transaction 안에서 실행되어야 Lazy Loading 등이 처리됨. readOnly =true 옵션을 주면 조회에 최적화
@RequiredArgsConstructor  //8 롬복 관련 어노테이션 : final이 있는 필드만 가지고 생성자를 만듬
public class MemberService {
    private final MemberRepository memberRepository;  //7 생성자에 주입하기 때문에 이건 이제 변경할 일이 없어서 final 줌
    //@Autowired  //6 생성자가 하나인 경우에는 @Autowired를 명시하지 않아도 스프링이 알아서 주입해줌
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }


    /* MemberRepository @Autowired Injection 방법
    //1 : 객체에 바로 @Autowired 주입 --> 외부 접근 어려움
    @Autowired
    private MemberRepository memberRepository;

    //2 :  setter 메서드를 통해 목데이터 주입 가능
    //        ex) 테스트 코드 작성 시 목데이터 주입하는 것이 필드에 주입하는 것보다 용이
    @Autowired
    public void setMemberRepository(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    //3 : 생성자 Injection
    */
    /**
     * 회원 가입
     **/
    @Transactional  //5 전체 클래스에 readOnly=true 옵션을 주고 하위 메서드에 readOnly=false default 옵션을 주면 하위 메서드엔 해당 옵션이 우선적으로 처리됨
    public Long join(Member member) {
        validateDuplicateMember(member);  //2 중복 회원 검증 기능
        memberRepository.save(member);  //1 save메서드를 이용하여 회원정보 DB에 저장하는 기능
        return member.getId();
    }
    //6 같은 이름을 가진 회원이 동시에 회원가입을 진행하면 문제가 될 수 있으므로 실무에서는 이름에 unique 제약조건을 넣어서 해당 이슈 방어 필요

    /**
     * 중복 회원 검증
     **/
    private void validateDuplicateMember(Member member) {
        //EXCEPTION
        List<Member> findMembers = memberRepository.findByName(member.getName());  //3 memberRepository에 같은 이름이 존재하는지 조회
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    /**
     * 회원 조회
     **/
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }
    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }


}
