package jpabook.jpashop.repository;

import jpabook.jpashop.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository                         //1 @Repository를 설정하면 ComponentScan에 의해 자동으로 스프링빈에서 관리
@RequiredArgsConstructor  //9 밑에 @Autowired로 변경했으니까 이거 집어넣어주고 private final로 변경하여 코드에 통일성을 준다
public class MemberRepository {

    //@Autowired  //7 Spring data JPA가 지원해줘서 @PersistenceContext를 @Autowired로 바꿀수잇고
    //@PersistenceContext
    private final EntityManager em;        //2 스프링이 자동으로 EM를 만들어서 여기에 inject 해줌

    /**회원 저장**/
    public void save(Member member) {
        em.persist(member);          //3 member 저장
    }
    /**회원 아이디로 조회**/
    public Member findOne(Long id) {
        return em.find(Member.class, id);  //4 JPA가 제공하는 find 메서드로 id값을 넘기면 멤버를 찾아서 반환
    }

    /**전체 회원 조회**/
    public List<Member> findAll() {
        return em.createQuery("select m from Member m", Member.class)  //5 모든 항목을 찾을 때는, JPQL을 작성. JPQL은 SQL과 거의 유사하지만 from의 대상이 테이블이 아니라 엔티티라는 점이 다름
                .getResultList();
    }

    /**회원 이름으로 조회**/
    public List<Member> findByName(String name) {  //6파라미터 바인딩으로 특정 이름에 대한 회원 조회 기능
        return em.createQuery("select m from Member m where m.name = :name", Member.class)
                .setParameter("name", name)
                .getResultList();
    }


}
