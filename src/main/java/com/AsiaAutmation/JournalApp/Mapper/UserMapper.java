package com.AsiaAutmation.JournalApp.Mapper;

import com.AsiaAutmation.JournalApp.Dto.UserResponseDTO;
import com.AsiaAutmation.JournalApp.Dto.UserRightsUpdateRequest;
import com.AsiaAutmation.JournalApp.Entity.Users;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);
    UserResponseDTO toDto(Users user);
    void updateRoles(UserRightsUpdateRequest request, @MappingTarget Users user);
}
