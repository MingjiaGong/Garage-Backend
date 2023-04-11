package com.cs673.backend.serviceImp;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.log.Log;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cs673.backend.DTO.UserDTO;
import com.cs673.backend.entity.User;
import com.cs673.backend.mapper.UserMapper;
import com.cs673.backend.repository.UserRepository;
import com.cs673.backend.service.UserService;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User>  implements UserService {
    private static final Log LOG = Log.get();
    @Autowired
    private UserRepository userRepository;

    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User findByUsernameAndPassword(String username, String password) {
        return userRepository.findByUsernameAndPassword(username, password);
    }
    @Override
    public User register(UserDTO userDTO) {
        User one = getUserInfo(userDTO);
        if (one == null) {
            one = new User();
            BeanUtil.copyProperties(userDTO, one, true);
            save(one);
        } else {
            throw new ServiceException("User already exists！");
        }
        return one;
    }

    private User getUserInfo(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        queryWrapper.eq("phone", userDTO.getPhone());
        queryWrapper.eq("email", userDTO.getEmail());
        queryWrapper.eq("address", userDTO.getAddress());
        queryWrapper.eq("Q1", userDTO.getQ1());
        queryWrapper.eq("A1", userDTO.getA1());
        queryWrapper.eq("Q2", userDTO.getQ2());
        queryWrapper.eq("A2", userDTO.getA2());
        User one;
        try {
            one = getOne(queryWrapper);
        } catch (Exception e) {
            LOG.error(e);
            throw new ServiceException("System error！");
        }
        return one;
    }

}
