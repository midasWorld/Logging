package com.midas.logging.domain.member;

import java.util.List;

public interface MemberRepositoryCustom {

	List<Member> findAll(MemberSearchDto search);
}
