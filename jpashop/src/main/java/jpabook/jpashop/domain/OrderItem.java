package jpabook.jpashop.domain;

import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Getter @Setter
public class OrderItem {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long Id;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int OrderPrice;
    private int count;

    //==생성 메서드==//
    //주문을 생성하기 전에 주문아이템을 구성하는 요소들을 넣어 아이템을 생성함
    public static OrderItem createOrderItem(Item item, int orderPrice, int count) {
        OrderItem orderItem = new OrderItem();
        orderItem.setItem(item);
        orderItem.setOrderPrice(orderPrice);
        orderItem.setCount(count);

        item.removeStock(count);
        return orderItem;
    }

    //==비즈니스 로직==//
    public void cancel() {
        getItem().addStock(count);  //재고수량 원복하기 -- 주문이 취소되었으므로 재고수량을 다시 채워준다
    }

    public int getTotalPrice() {
        return getOrderPrice() * getCount(); // 주문 총액 = 주문금액 * 주문개수
    }
}
