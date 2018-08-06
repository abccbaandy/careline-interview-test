package com.careline.interview.test;

import com.careline.interview.test.Entity.Member;
import com.careline.interview.test.Entity.MemberModifyInfo;
import com.careline.interview.test.dao.MemberDao;
import com.careline.interview.test.dao.MemberModifyInfoDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class CustomUserDetailsManager implements UserDetailsManager {

    @Autowired
    MemberDao memberDao;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    MemberModifyInfoDao memberModifyInfoDao;

    private void saveModifyLog(String info) {
        MemberModifyInfo memberModifyInfo = new MemberModifyInfo();
        memberModifyInfo.setModifyInfo(info);
        memberModifyInfoDao.save(memberModifyInfo);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return memberDao.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("member not found"));
    }

    @Override
    public void createUser(UserDetails user) {
        Member member = ((Member) user);
        member.setPassword(passwordEncoder.encode(user.getPassword()));
        memberDao.save(member);
    }

    @Override
    public void updateUser(UserDetails user) {
        Member oldMember = ((Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        oldMember = memberDao.getOne(oldMember.getId());
        Member newMember = ((Member) user);
        oldMember.setEmail(newMember.getEmail());
        oldMember.setUsername(newMember.getUsername());
        memberDao.save(oldMember);

        SecurityContextHolder.getContext().setAuthentication(new UsernamePasswordAuthenticationToken(oldMember, null, AuthorityUtils.NO_AUTHORITIES));

        saveModifyLog("update profile");
    }

    @Override
    public void deleteUser(String username) {
        throw new UnsupportedOperationException("not implemented yet");
    }

    @Override
    public void changePassword(String oldPassword, String newPassword) {
        Member member = ((Member) SecurityContextHolder.getContext().getAuthentication().getPrincipal());

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(member.getEmail(), oldPassword));

        member.setPassword(passwordEncoder.encode(newPassword));
        memberDao.save(member);

        saveModifyLog("update password");
    }

    @Override
    public boolean userExists(String username) {
        throw new UnsupportedOperationException("not implemented yet");
    }
}