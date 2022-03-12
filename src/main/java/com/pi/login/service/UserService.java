package com.pi.login.service;

import java.util.List;
import java.util.function.Supplier;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.pi.login.model.RoleType;
import com.pi.login.model.User;
import com.pi.login.repository.UserRepository;

@RequiredArgsConstructor
@Service
public class UserService {

	private final UserRepository userRepository;

	private final BCryptPasswordEncoder encoder;
	
	@org.springframework.transaction.annotation.Transactional(readOnly = true)//정합성(같은데이터)
	public User 회원찾기(String username){
		User user= userRepository.findByUsername(username).orElseGet(()->{
			return new User();
		});
		return user;
	}

	@org.springframework.transaction.annotation.Transactional
	public void 회원탈퇴 (Long id) {
	 userRepository.deleteById(id);
			
	}

	@org.springframework.transaction.annotation.Transactional
	public List<User> 회원다찾자(){
		return userRepository.findAll();
	}

	@org.springframework.transaction.annotation.Transactional
	public User 회원찾자(@PathVariable Long id){
		User user = userRepository.findById(id).orElseThrow(new Supplier<IllegalArgumentException>() {
		@Override
		public IllegalArgumentException get() {
		return new IllegalArgumentException("해당 유저는 없습니다.");
		}});
		return user;
	}

	@org.springframework.transaction.annotation.Transactional
	public void 회원가입(User user) {

		String rawPassword = user.getPassword();
		String encPassword = encoder.encode(rawPassword);

		User user1 = new User().builder()
					.role(RoleType.USER)
					.createDate(user.getCreateDate())
					.email(user.getEmail())
					.id(null)
					.oauth(user.getOauth())
					.userId(user.getUserId())
					.password(encPassword)
					.phone(user.getPhone())
					.username(user.getUsername())
					.build();
		userRepository.save(user1);
		}

	@org.springframework.transaction.annotation.Transactional
	public void 회원수정(User user) {
		User persistance = userRepository.findById(user.getId()).orElseThrow(()->{
			return new IllegalArgumentException("회원찾기 실패");
		});
		
		if(persistance.getOauth()==null||persistance.getOauth().equals("")) {
			String rawPassword = user.getPassword();
			String encPassword = encoder.encode(rawPassword);
			persistance = new User().builder()
					.password(encPassword)
					.build();
//			persistance.setPassword(encPassword);
			userRepository.save(persistance);
		}
		persistance = new User().builder()
				.phone(user.getPhone())
				.email(user.getEmail())
				.build();
		userRepository.save(persistance);
	}



		
		/*
		 * User user1 = userRepository.getById(user1.getUserId()).orElseThrow(()->{
		 * return new IllegalArgumentException("회원찾기 실패"); });
		 */
		
//		if(persistance.getOauth()==null||persistance.getOauth().equals("")) {
//			String rawPassword = user.getPassword();
//			String encPassword = encoder.encode(rawPassword);
//			persistance.setPassword(encPassword);
//			 persistance.setUsername(user.getUsername());
//			 persistance.setUserId(user.getUserId());
//			 persistance.setEmail(user.getEmail());
//		}
		
	
		
			}

