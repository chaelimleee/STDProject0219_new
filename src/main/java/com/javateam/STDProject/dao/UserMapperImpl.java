package com.javateam.STDProject.dao;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Repository;

import com.javateam.STDProject.domain.Users;



@Repository
public class UserMapperImpl implements UserMapper {
	
	@Autowired
	SqlSession sqlSession;

	@Override
	public Users getUserByUsername(String userName) {
		return sqlSession.getMapper(UserMapper.class).getUserByUsername(userName);
	}

	@Override
	public List<Role> getUserRolesByUsername(String userName) {
		return sqlSession.getMapper(UserMapper.class).getUserRolesByUsername(userName);
	}

	@Override
	public int hasUsername(String username) {
		return sqlSession.getMapper(UserMapper.class).hasUsername(username);
	}

//	@Override
//	public Users loadUserByUsername(String userName) {
//		return sqlSession.getMapper(UserMapper.class).loadUserByUsername(userName);
//	}

	@Override
	public void insertUser(Users users) {

		System.out.println("sql세션 확인 before >>>");
		sqlSession.getMapper(UserMapper.class).insertUser(users);
		System.out.println("sql세션 확인 >>>");
	}

	@Override
	public void insertUserRoles(String username, String role) {
		
		sqlSession.getMapper(UserMapper.class).insertUserRoles(username, role);
	}

}
