package com.psjw.kakaoshareprototype.service;

import com.psjw.kakaoshareprototype.dto.UserRequestDto;
import com.psjw.kakaoshareprototype.mapper.UserMapper;
import com.psjw.kakaoshareprototype.model.User;
import com.psjw.kakaoshareprototype.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * packageName : com.psjw.kakaoshareprototype.service
 * fileName : UserService
 * author : psjw
 */
@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public UserRequestDto save(UserRequestDto userDto){
        User user = UserMapper.INSTNACE.toEntity(userDto);
        userRepository.save(user);
        return userDto;
    }
}
