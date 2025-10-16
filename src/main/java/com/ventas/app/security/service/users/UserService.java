package com.ventas.app.security.service.users;

import com.ventas.app.security.dto.UserDto;
import com.ventas.app.security.service.exceptions.ServiceException;

public interface UserService {
	void add(UserDto userDto) throws ServiceException;
}
