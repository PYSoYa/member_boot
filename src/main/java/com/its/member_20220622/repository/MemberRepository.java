package com.its.member_20220622.repository;


import com.its.member_20220622.dto.MemberDTO;
import com.its.member_20220622.entity.MemberEntity;
import org.springframework.stereotype.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


@Repository
public interface MemberRepository extends JpaRepository<MemberEntity, Long> {
//    MemberEntity findByMemberNameAndMemberPassword
//            (String memberEmail, String memberPassword);
    // select * from member_table where memberEmail = ?
    // 리턴타입: MemberEntity
    // 매개변수: memberEmail(String)
    Optional<MemberEntity> findByMemberEmail(String memberEmail);
}