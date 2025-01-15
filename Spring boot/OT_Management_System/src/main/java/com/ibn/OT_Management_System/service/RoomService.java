package com.ibn.OT_Management_System.service;

import com.ibn.OT_Management_System.DTO.RoomDto;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface RoomService {

    RoomDto createRoom(RoomDto roomDto);

    RoomDto updateRoom(RoomDto roomDto,Long id);

    void deleteRoom(Long id);

    List<RoomDto> getAllRoom(Sort sort);

    RoomDto getSingleRoom(Long id);



}
