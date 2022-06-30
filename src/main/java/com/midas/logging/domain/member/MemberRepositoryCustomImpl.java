package com.midas.logging.domain.member;

import static com.midas.logging.domain.member.QMember.member;

import java.time.LocalDate;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class MemberRepositoryCustomImpl implements MemberRepositoryCustom {

	private final JPAQueryFactory queryFactory;

	@Override
	public List<Member> findAll(MemberSearchDto search) {
		return queryFactory
			.select(member)
			.from(member)
			.where(
				emailEq(search.getEmail()),
				nameEq(search.getName()),
				ageGoe(search.getStartAge()),
				ageLoe(search.getEndAge()),
				createdAtGoe(search.getStartCreatedDate()),
				createdAtLoe(search.getEndCreatedDate())
			)
			.fetch();
	}

	private BooleanExpression emailEq(String email) {
		return email == null ? null : member.email.eq(email);
	}

	private BooleanExpression nameEq(String name) {
		return name == null ? null : member.name.eq(name);
	}

	private BooleanExpression ageGoe(Integer age) {
		return age == null ? null : member.age.goe(age);
	}

	private BooleanExpression ageLoe(Integer age) {
		return age == null ? null : member.age.loe(age);
	}

	private BooleanExpression createdAtGoe(LocalDate createdAt) {
		return createdAt == null ? null : member.createdAt.after(createdAt.atStartOfDay());
	}

	private BooleanExpression createdAtLoe(LocalDate createdAt) {
		return createdAt == null ? null : member.createdAt.before(createdAt.plusDays(1).atStartOfDay());
	}
}
