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
    @Column(name="order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="member_id") //name은 FK 이름임, 여기 member필드를 변경해야 fk값도 변경이 이루어짐
    private Member member;
    
//    모든 엔티디는 기본적으로 persist를 각각 각자 처리 해야함 그러나,
//    CascadeType.ALL -> Order를 persist하면 OrderItem도 자동으로 persist 처리됨
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    private LocalDateTime orderDate; //주문시간

    @Enumerated(EnumType.STRING)
    private OrderStatus status; //주문상태 [ORDER, CANCEL]

    //양방향 연관관계 메서드
    public void setMember(Member member) {
        this.member = member;
        member.getOrders().add(this);
    }
/*
*   Member member = new Member();
*   Order order = new Order();
*   
* 원래는 이렇게 해야 하는데 
*   member.getOrders().add(order);
*   order.setMember(member);
* 
* 번거로우니 편의 메서드를 작성하면
*   order.setMember(member); 만 하면 됨
* 
* */

    public void addOrderItem(OrderItem orderItem) {
        orderItems.add(orderItem);
        orderItem.setOrder(this);
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
        delivery.setOrder(this);
    }
}
