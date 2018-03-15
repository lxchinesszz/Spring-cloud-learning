package org.smile.provider.service;

import org.smile.model.User;
import org.springframework.stereotype.Service;

/**
 * @Package: org.smile.provider
 * @Description:
 * @author: liuxin
 * @date: 2017/12/21 下午3:43
 */
@Service
public interface UserService {
    User getUser();
}
