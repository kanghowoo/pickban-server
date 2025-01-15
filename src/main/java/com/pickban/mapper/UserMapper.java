package com.pickban.mapper;

import org.mapstruct.Mapper;

import com.pickban.dto.UserApiShowResponse;
import com.pickban.entity.User;

@Mapper(config = BaseMapper.class)
public interface UserMapper {

    UserApiShowResponse userToApiShowResponse(User user);
}
