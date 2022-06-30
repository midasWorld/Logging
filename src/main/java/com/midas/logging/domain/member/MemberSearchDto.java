package com.midas.logging.domain.member;

import java.time.LocalDate;

import javax.validation.constraints.AssertTrue;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Getter;

@Getter
public class MemberSearchDto {

	private String email;
	private String name;
	private Integer startAge;
	private Integer endAge;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate startCreatedDate;

	@DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
	private LocalDate endCreatedDate;

	public MemberSearchDto(
		String email,
		String name,
		Integer startAge,
		Integer endAge,
		LocalDate startCreatedDate,
		LocalDate endCreatedDate
	) {
		this.email = email;
		this.name = name;
		this.startAge = startAge;
		this.endAge = endAge;
		this.startCreatedDate = startCreatedDate;
		this.endCreatedDate = endCreatedDate;
	}

	@AssertTrue(message = "startCreatedDate must be greater or equals than endCreatedDate")
	public boolean isValidPeriod() {
		if (startCreatedDate == null || endCreatedDate == null) {
			return true;
		}

		return startCreatedDate.isBefore(endCreatedDate);
	}
}
