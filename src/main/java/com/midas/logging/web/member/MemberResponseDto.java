package com.midas.logging.web.member;

import java.time.LocalDateTime;

import com.midas.logging.domain.member.Member;

import lombok.Getter;

@Getter
public class MemberResponseDto {

	private final Long id;
	private final String email;
	private final String name;
	private final int age;
	private final LocalDateTime createdAt;

	public MemberResponseDto(Member member) {
		this.id = member.getId();
		this.email = member.getEmail();
		this.name = member.getName();
		this.age = member.getAge();
		this.createdAt = member.getCreatedAt();
	}
}
