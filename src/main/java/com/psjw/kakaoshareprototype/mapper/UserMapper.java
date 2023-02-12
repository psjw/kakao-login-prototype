package com.psjw.kakaoshareprototype.mapper;

import com.psjw.kakaoshareprototype.dto.UserRequestDto;
import com.psjw.kakaoshareprototype.dto.UserResponseDto;
import com.psjw.kakaoshareprototype.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * packageName : com.psjw.kakaoshareprototype.mapper
 * fileName : UserMapper
 * author : psjw
 */
@Mapper
public interface UserMapper {
    UserMapper INSTNACE = Mappers.getMapper(UserMapper.class);

    @Mapping(target = "id", ignore = true)
    @Mapping(source = "password", target = "password", qualifiedByName = "encryptPassword")
    @Mapping(target = "createDate", expression = "java(java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()))")
    User toEntity(UserRequestDto dto);

    UserResponseDto toReponseDto(User user);

    UserRequestDto toRequestDto(User user);

    @Named("encryptPassword")
    default String encryptPassword(String password){
        return new BCryptPasswordEncoder().encode(password);
    }
}
