package com.psjw.kakaoshareprototype.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

/**
 * packageName : com.psjw.kakaoshareprototype.dto
 * fileName : User
 * author : psjw
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserRequestDto {
    private int id;
    private String username;
    private String password;
    private String email;
    private String role;
    private Timestamp createDate;
}
