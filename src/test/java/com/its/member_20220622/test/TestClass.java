package com.its.member_20220622.test;

import com.its.member_20220622.dto.MemberDTO;
import com.its.member_20220622.entity.MemberEntity;
import com.its.member_20220622.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import javax.transaction.Transactional;
import java.util.stream.IntStream;
import static org.assertj.core.api.Assertions.*;


@SpringBootTest
public class TestClass {
    @Autowired
    private MemberService memberService;

    @Test
    @Transactional
    @Rollback(value = true)
    public void saveTest(){
        MemberDTO memberDTO = new MemberDTO("qkrwnl01@gmail.com", "1234",
                "박윤석", 26, "01032215234");
        Long id = memberService.save(memberDTO);
        MemberDTO saveMemberDTO = memberService.findById(id);

        assertThat(memberDTO.getMemberEmail()).isEqualTo(saveMemberDTO.getMemberEmail());
    }
    @Test
    @Transactional
    @Rollback(value = true)
    public void loginTest(){
        MemberDTO memberDTO = new MemberDTO("qkrwnl01@gmail.com", "1234",
                "박윤석", 26, "01032215234");
        Long id = memberService.save(memberDTO);
        MemberDTO saveMemberDTO = memberService.findById(id);
        boolean loginMemberDTO = memberService.login
                ("qkrwnl01@gmail.com", "1234");
        assertThat(loginMemberDTO).isEqualTo(true);
    }
}
