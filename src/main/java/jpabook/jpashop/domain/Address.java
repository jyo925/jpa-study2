package jpabook.jpashop.domain;

import lombok.Getter;

import javax.persistence.Embeddable;

//값타입(변경불가하도록 설계)
@Embeddable
@Getter
public class Address {

//    JPA 스팩 리플랙션, 프록시 등 기술 사용시 기본생성자 필요
    protected Address() {
    }

    //    생성할 때만 값이 셋팅되도록
    public Address(String city, String street, String zipcode) {
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }

    private String city;
    private String street;
    private String zipcode;

}
