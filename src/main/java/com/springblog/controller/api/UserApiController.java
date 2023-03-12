package com.springblog.controller.api;

import com.springblog.dto.ResponseDto;
import com.springblog.dto.UserDto;
import com.springblog.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RequestMapping("/api")
@RestController
public class UserApiController {
  private final UserService userService;

  @PostMapping("/signup")
  public ResponseDto<Integer> singup(@RequestBody UserDto userDto) {
    userService.join(userDto);
    return ResponseDto.ofSuccess(1);
  }

  @PutMapping("/user")
  public ResponseDto<Integer> update(@RequestBody UserDto userDto) {
    userService.update(userDto);
    return ResponseDto.ofSuccess(1);
  }
}