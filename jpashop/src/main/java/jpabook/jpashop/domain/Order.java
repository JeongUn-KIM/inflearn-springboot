package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
@Getter
@Setter
public class Order {

    @Id
    @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    @ManyToOne                      //1
    @JoinColumn(name = "member_id") //2
    private Member member;          //3

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();


    //배송정보
    @OneToOne  //JPA에서 일대일관계는 FK를 어디에 둬도 상관 없음 / Order에 두자!
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    //주문시간
    private LocalDateTime orderDate;

    //주문상태
    @Enumerated(EnumType.STRING)
    private OrderStatus status;


}
