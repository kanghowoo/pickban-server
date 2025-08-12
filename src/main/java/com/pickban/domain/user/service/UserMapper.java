package com.pickban.domain.user.service;

import org.mapstruct.Mapper;

import com.pickban.domain.user.controller.dto.UserApiShowResponse;
import com.pickban.domain.user.domain.model.User;
import com.pickban.global.mapper.BaseMapper;

@Mapper(config = BaseMapper.class)
public interface UserMapper {

    UserApiShowResponse userToApiShowResponse(User user);
}
