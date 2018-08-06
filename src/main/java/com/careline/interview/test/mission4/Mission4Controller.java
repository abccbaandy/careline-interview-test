package com.careline.interview.test.mission4;

import com.careline.interview.test.dao.MemberDao;
import com.careline.interview.test.mission4.vo.MemberListVo;
import com.careline.interview.test.mission4.vo.MemberVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequestMapping("/mission4")
public class Mission4Controller {

    @Autowired
    MemberDao memberDao;

    @GetMapping("/getAllMembers")
    public MemberListVo getAllMembers() {
        return MemberListVo.builder().memberList(memberDao.findAll().stream().map(m ->
                MemberVo.builder()
                        .email(m.getEmail())
                        .memberId(m.getId())
                        .name(m.getUsername())
                        .build()
        ).collect(Collectors.toList())).build();
    }
}
