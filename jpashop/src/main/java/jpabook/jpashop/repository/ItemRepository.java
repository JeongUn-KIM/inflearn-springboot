package jpabook.jpashop.repository;

import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    //상품 저장
    public void save(Item item) {
        if (item.getId() == null) { //item은 처음 저장할 땐 Id가 없음
            em.persist(item);  //item 저장
        } else {    //처음 저장하는 item이 아니면, merge사용 (update와 비슷한 용도)
            em.merge(item);
        }
    }

    //단일 상품 조회
    public Item findOne(Long id) {
        return em.find(Item.class, id);
    }

    //전체 상품 조회
    public List<Item> findAll() {
        return em.createQuery("select i from Item i", Item.class)
                .getResultList();
    }

}
