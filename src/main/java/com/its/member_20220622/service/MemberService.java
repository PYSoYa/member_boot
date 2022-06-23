package com.its.member_20220622.service;

import com.its.member_20220622.dto.MemberDTO;
import com.its.member_20220622.entity.MemberEntity;
import com.its.member_20220622.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberService<UserDetails> {
    private final MemberRepository memberRepository;

    public Long save(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toEntity(memberDTO);
        return memberRepository.save(memberEntity).getId();
    }

    public MemberDTO findById(Long id) {
        Optional<MemberEntity> om = memberRepository.findById(id);
        if (om.isPresent()) {
            MemberEntity memberEntity = om.get();
            return MemberDTO.toDTO(memberEntity);
            // return MemberDTO.toDTO(om.get());
        } else {
            return null;
        }
    }

    public MemberDTO login(MemberDTO memberDTO) {
        MemberEntity member = emailCheck(memberDTO.getMemberEmail());
        if (member.getMemberPassword().equals(memberDTO.getMemberPassword())) {
            return MemberDTO.toDTO(member);
        } else {
            return null;
        }
    }

    public MemberEntity emailCheck(String memberEmail) {
        Optional<MemberEntity> member = memberRepository.findByMemberEmail(memberEmail);
        if (member.isPresent()) {
            return member.get();
        } else {
            return null;
        }
    }

    public List<MemberDTO> findAll() {
        List<MemberEntity> memberEntityList = memberRepository.findAll();
        List<MemberDTO> memberDTOList = new ArrayList<>();
        for(MemberEntity list : memberEntityList) {
            memberDTOList.add(MemberDTO.toDTO(list));
        }
        return memberDTOList;
    }

    public void delete(Long id) {
        memberRepository.deleteById(id);
    }

    public void update(MemberDTO memberDTO) {
        MemberEntity memberEntity = MemberEntity.toUpdateEntity(memberDTO);
        memberRepository.save(memberEntity);
    }
}
