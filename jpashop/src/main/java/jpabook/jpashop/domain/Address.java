package jpabook.jpashop.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;

@Embeddable  //1
@Getter //2 값타입은 변경 불가능하게 설정해야 함. 처음 만들어질때만 값이 세팅되고 Setter는 제공 x -> 값 변경 불가
public class Address {

    private String city;
    private String street;
    private String zipcode;

    protected Address() {  //빈 생성자로 초기화. JPA에서 엔티티나 임베디드 타입은 자바 기본 생성자를 public/protected 로 설정해야함. 후자가 외부접근으로부터 안전함.
    }


    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
