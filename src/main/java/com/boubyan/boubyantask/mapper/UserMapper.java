package com.boubyan.boubyantask.mapper;


import com.boubyan.boubyantask.entity.User;
import com.boubyan.boubyantask.model.dto.UserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import static org.mapstruct.NullValueCheckStrategy.ALWAYS;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, nullValueCheckStrategy = ALWAYS)

public interface UserMapper {
    UserDTO mapUserToUserDto(User user);

    User mapUserDTOToUser(UserDTO userDTO);
}
