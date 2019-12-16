package com.sky.controller;

import com.sky.dto.GroupDTO;
import com.sky.service.GroupService;
import com.sky.service.GroupUserService;
import com.sky.service.TransferService;
import com.sky.service.UserService;
import com.sky.utils.ListUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/group")
@Slf4j
public class GroupController {

    private UserService userService;
    private GroupService groupService;
    private GroupUserService groupUserService;
    private TransferService transferService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setGroupUserService(GroupUserService groupUserService) {
        this.groupUserService = groupUserService;
    }

    @Autowired
    public void setGroupService(GroupService groupService) {
        this.groupService = groupService;
    }

    @Autowired
    public void setTransferService(TransferService transferService) {
        this.transferService = transferService;
    }

    @GetMapping("/detail")
    public ModelAndView detail(@Valid Integer groupId,
                               Map<String, Object> map) {
        GroupDTO group = transferService.transfer(groupService.findById(groupId));
        map.put("group", group);
        return new ModelAndView("gdetail", map);
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "4") Integer size,
                             HttpServletRequest request,
                             Map<String, Object> map) {
        int userId = userService.getUserId(request);
        List<GroupDTO> groupList = groupUserService.findByUserId(userId);
        Page<GroupDTO> groupPage = ListUtil.listConvertToPage(groupList, PageRequest.of(page - 1, size));

        map.put("groupPage", groupPage);
        map.put("page", page);
        map.put("size", size);
        return new ModelAndView("glist", map);
    }
}
