package com.lec.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.lec.domain.Member;

public interface AdminService {

	Page<Member> getAdminList(Pageable pageable, String searchType, String searchWord);

}
