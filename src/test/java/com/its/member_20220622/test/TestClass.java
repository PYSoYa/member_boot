package com.its.member_20220622.test;

import com.its.member_20220622.dto.MemberDTO;
import com.its.member_20220622.entity.MemberEntity;
import com.its.member_20220622.service.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.lang.reflect.Member;
import java.util.List;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class TestClass {
    @Autowired
    private MemberService memberService;

    public MemberDTO newMember() {
        MemberDTO member = new MemberDTO
                ("테스트용이메일", "테스트용비밀번호", "테스트용이름",26,"테스트용전화번호");
        return member;
    }

    @Test
    @Transactional
    @Rollback(value = true)
    public void saveTest(){
        Long id = memberService.save(newMember());
        MemberDTO saveMemberDTO = memberService.findById(id);

        assertThat(newMember().getMemberEmail()).isEqualTo(saveMemberDTO.getMemberEmail());
    }
    @Test
    @Transactional
    @Rollback
    @DisplayName("로그인 테스트")
    public void loginTest(){
        final String memberEmail = "로그인용이메일";
        final String memberPassword = "로그인용비밀번호";
        String memberName = "로그인용이름";
        int memberAge = 99;
        String memberPhone = "로그인용전화번호";
        MemberDTO memberDTO = new MemberDTO(memberEmail, memberPassword, memberName, memberAge, memberPhone);
        Long id = memberService.save(memberDTO);
        // 로그인 객체 생성 후 로그인
        MemberDTO loginMemberDTO = new MemberDTO();
        loginMemberDTO.setMemberEmail(memberEmail);
        loginMemberDTO.setMemberPassword(memberPassword);
        MemberDTO loginResult = memberService.login(loginMemberDTO);
        // 로그인 결과가 not null 이면 테스트 통과
        assertThat(loginResult).isNotNull();
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("회원목록 테스트")
    public void findAllTest(){
        for (int i = 0; i < 10; i++){
            memberService.save(new MemberDTO
                    ("테스트용이메일"+i,
                            "테스트용비밀번호"+i,
                            "테스트용이름"+i,26+i,"테스트용전화번호"+i));

        }
        List<MemberDTO> memberDTOList = memberService.findAll();
        assertThat(memberDTOList.size()).isEqualTo(10);
    }

    @Test
    @Transactional
    @Rollback
    @DisplayName("회원정보수정 테스트")
    public void updateTest(){
       Long id = memberService.save(newMember());

       MemberDTO memberDTO = memberService.findById(id);
       memberDTO.setMemberName("이름수정");

       memberService.update(memberDTO);
       MemberDTO updateDTO = memberService.findById(id);
       assertThat(memberDTO.getMemberName()).isNotEqualTo(updateDTO.getMemberName());
    }
}
