package com.psjw.kakaoshareprototype.mapper;

import com.psjw.kakaoshareprototype.dto.UserRequestDto;
import com.psjw.kakaoshareprototype.dto.UserRequestDto.UserRequestDtoBuilder;
import com.psjw.kakaoshareprototype.dto.UserResponseDto;
import com.psjw.kakaoshareprototype.dto.UserResponseDto.UserResponseDtoBuilder;
import com.psjw.kakaoshareprototype.model.User;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-02-18T20:00:32+0900",
    comments = "version: 1.4.2.Final, compiler: javac, environment: Java 19.0.2 (Oracle Corporation)"
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
        user.setProvider( dto.getProvider() );
        user.setProviderId( dto.getProviderId() );

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
        userRequestDto.provider( user.getProvider() );
        userRequestDto.providerId( user.getProviderId() );
        userRequestDto.createDate( user.getCreateDate() );

        return userRequestDto.build();
    }
}
