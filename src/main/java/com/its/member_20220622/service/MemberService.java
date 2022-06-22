package com.its.member_20220622.service;

import com.its.member_20220622.dto.MemberDTO;
import com.its.member_20220622.entity.MemberEntity;
import com.its.member_20220622.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService<UserDetails> {
    private final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.save(memberDTO);
       Long id = memberRepository.save(memberEntity).getId();
       return id;
    }
    public MemberDTO findById(Long id) {
        Optional<MemberEntity> memberEntityOptional = memberRepository.findById(id);
        if(memberEntityOptional.isPresent()){
            return MemberDTO.findById(memberEntityOptional.get());
        }else {
            return null;
        }
    }
    public boolean login(String memberEmail, String memberPassword){
        MemberDTO memberDTO = new MemberDTO();
       MemberEntity memberEntity =
               memberRepository.findByMemberNameAndMemberPassword(memberEmail, memberPassword);
        if(memberEntity.getMemberEmail() == memberDTO.getMemberEmail()){
            return true;
        }else{
            return false;
        }

    }
}
