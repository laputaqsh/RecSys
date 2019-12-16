package com.sky.controller;

import com.sky.dto.EventDTO;
import com.sky.service.EventService;
import com.sky.service.RecResService;
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
@RequestMapping("/event")
@Slf4j
public class EventController {

    private UserService userService;
    private EventService eventService;
    private TransferService transferService;
    private RecResService recResService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setEventService(EventService eventService) {
        this.eventService = eventService;
    }

    @Autowired
    public void setTransferService(TransferService transferService) {
        this.transferService = transferService;
    }

    @Autowired
    public void setRecResService(RecResService recResService) {
        this.recResService = recResService;
    }

    @GetMapping("/detail")
    public ModelAndView detail(@Valid Integer eventId,
                               Map<String, Object> map) {
        EventDTO event = transferService.transfer(eventService.findById(eventId));
        map.put("event", event);
        return new ModelAndView("edetail", map);
    }

    @GetMapping("/list")
    public ModelAndView list(@RequestParam(value = "page", defaultValue = "1") Integer page,
                             @RequestParam(value = "size", defaultValue = "3") Integer size,
                             HttpServletRequest request,
                             Map<String, Object> map) {
        int userId = userService.getUserId(request);
        List<EventDTO> eventList = recResService.findByUserId(userId);
        Page<EventDTO> eventPage = ListUtil.listConvertToPage(eventList, PageRequest.of(page - 1, size));

        map.put("eventPage", eventPage);
        map.put("page", page);
        map.put("size", size);
        return new ModelAndView("elist", map);
    }

    @PostMapping("search")
    public ModelAndView search() {
        return new ModelAndView("elist");
    }
}
