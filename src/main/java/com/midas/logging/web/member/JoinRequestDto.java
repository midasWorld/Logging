package com.midas.logging.web.member;

import com.midas.logging.domain.member.Member;

import lombok.Getter;

@Getter
public class JoinRequestDto {

	private final String email;
	private final String password;
	private final String name;
	private final int age;

	public JoinRequestDto(String email, String password, String name, int age) {
		this.email = email;
		this.password = password;
		this.name = name;
		this.age = age;
	}

	public Member toEntity() {
		return Member.builder()
			.email(email)
			.password(password)
			.name(name)
			.age(age)
			.build();
	}
}
