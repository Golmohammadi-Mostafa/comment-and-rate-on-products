package com.egc.shopping.mapper;


import com.egc.shopping.domain.User;
import com.egc.shopping.dto.UserResponseDTO;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.util.CollectionUtils;

@Mapper(componentModel = "spring")
public interface UserMapper {



    User userResponseDTOtoUser(UserResponseDTO dto);

    UserResponseDTO userToUserResponseDTO(User user);

    @AfterMapping
    default void calledAfter(User user, @MappingTarget UserResponseDTO dto) {

        if (!CollectionUtils.isEmpty(user.getComments())) {
            dto.setComments(user.getComments());
        }
        if (!CollectionUtils.isEmpty(user.getRates())) {
            dto.setRates(user.getRates());
        }
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            dto.setRoles(user.getRoles());
        }
    }
}
