package com.example.todolist.entity.member;

import com.example.todolist.dto.member.MemberRequestDTO;
import com.example.todolist.entity.base.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Member extends BaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "member_id")
    private Long id; // 고유 식별자
    private String memberEmail; // 이메일
    private String memberPassword; // 비밀번호
    private String memberName; // 이름
    private String memberPhone; // 전화번호

    /** 메서드 **/
    public void memberUpdate(MemberRequestDTO.MemberUpdateDTO memberUpdateDTO) {
        this.memberEmail = memberUpdateDTO.getMemberEmail();
        this.memberPassword = memberUpdateDTO.getMemberPassword();
        this.memberName = memberUpdateDTO.getMemberName();
        this.memberPhone = memberUpdateDTO.getMemberPhone();
    }

    /** 생성자 **/
    protected Member() {

    }

    public Member(String memberEmail, String memberPassword) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
    }

    public Member(String memberEmail, String memberPassword, String memberName, String memberPhone) {
        this.memberEmail = memberEmail;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberPhone = memberPhone;
    }
}
