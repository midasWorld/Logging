package com.midas.logging.service;

import static com.google.common.base.Preconditions.checkArgument;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.midas.logging.domain.member.Member;
import com.midas.logging.domain.member.MemberRepository;
import com.midas.logging.domain.member.MemberSearchDto;

import lombok.RequiredArgsConstructor;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class MemberService {
	private final MemberRepository memberRepository;

	@Transactional
	public Member join(Member member) {
		return memberRepository.save(member);
	}

	public Member login(String email, String password) {
		Member member = memberRepository.findByEmail(email)
			.orElseThrow(() -> new IllegalArgumentException("Could not found Member with email=" + email));

		checkArgument(
			member.getPassword().equals(password),
			"Password invalid with password=" + password
		);

		return member;
	}

	public List<Member> findAll(MemberSearchDto search) {
		return memberRepository.findAll(search);
	}
}
