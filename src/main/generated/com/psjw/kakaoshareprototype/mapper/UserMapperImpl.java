package com.psjw.kakaoshareprototype.mapper;

import com.psjw.kakaoshareprototype.dto.UserRequestDto;
import com.psjw.kakaoshareprototype.dto.UserRequestDto.UserRequestDtoBuilder;
import com.psjw.kakaoshareprototype.dto.UserResponseDto;
import com.psjw.kakaoshareprototype.dto.UserResponseDto.UserResponseDtoBuilder;
import com.psjw.kakaoshareprototype.model.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-12T20:32:11+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 11.0.15 (JetBrains s.r.o.)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public User toEntity(UserRequestDto dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setPassword( encryptPassword( dto.getPassword() ) );
        user.setUsername( dto.getUsername() );
        user.setEmail( dto.getEmail() );
        user.setRole( dto.getRole() );

        user.setCreateDate( java.sql.Timestamp.valueOf(java.time.LocalDateTime.now()) );

        return user;
    }

    @Override
    public UserResponseDto toReponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDtoBuilder userResponseDto = UserResponseDto.builder();

        userResponseDto.id( user.getId() );
        userResponseDto.username( user.getUsername() );
        userResponseDto.password( user.getPassword() );
        userResponseDto.email( user.getEmail() );
        userResponseDto.role( user.getRole() );
        userResponseDto.createDate( user.getCreateDate() );

        return userResponseDto.build();
    }

    @Override
    public UserRequestDto toRequestDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserRequestDtoBuilder userRequestDto = UserRequestDto.builder();

        userRequestDto.id( user.getId() );
        userRequestDto.username( user.getUsername() );
        userRequestDto.password( user.getPassword() );
        userRequestDto.email( user.getEmail() );
        userRequestDto.role( user.getRole() );
        userRequestDto.createDate( user.getCreateDate() );

        return userRequestDto.build();
    }
}
