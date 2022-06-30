package com.midas.logging.web.member;

import java.util.List;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.midas.logging.domain.member.Member;
import com.midas.logging.domain.member.MemberSearchDto;
import com.midas.logging.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
@RestController
public class MemberRestController {

	private final MemberService memberService;

	@PostMapping("/join")
	public MemberResponseDto join(@RequestBody JoinRequestDto request) {
		Member member = memberService.join(request.toEntity());

		return new MemberResponseDto(member);
	}

	@PostMapping
	public MemberResponseDto login(@RequestBody LoginRequestDto request) {
		Member member = memberService.login(request.getEmail(), request.getPassword());

		return new MemberResponseDto(member);
	}

	@GetMapping
	public List<MemberResponseDto> findAll(@Valid MemberSearchDto search) {
		List<Member> members = memberService.findAll(search);

		return members.stream()
			.map(MemberResponseDto::new)
			.toList();
	}

	@GetMapping("/{id}")
	public MemberResponseDto findById(@PathVariable Long id) {
		Member member = memberService.findById(id);

		return new MemberResponseDto(member);
	}
}
