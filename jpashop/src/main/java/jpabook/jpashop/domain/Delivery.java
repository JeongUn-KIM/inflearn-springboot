package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @OneToOne(mappedBy = "delivery")
    private Order order;

    @Embedded
    private Address address;

    @Enumerated(EnumType.STRING)  //디폴트가 Ordinary : 1,2,3... 순서대로 들어감 -> 중간에 다른 상태가 들어갈 수 없음 /String 사용하기
    private DeliveryStatus status;
}
