package com.ibn.OT_Management_System.controller;

import com.ibn.OT_Management_System.DTO.ApiResponse;
import com.ibn.OT_Management_System.DTO.RoomDto;
import com.ibn.OT_Management_System.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/room")
public class RoomController {

    @Autowired
    private RoomService roomService;

    @PostMapping("/create")
    public ResponseEntity<ApiResponse> createRoom(@RequestBody RoomDto roomDto){
        try {
            RoomDto room = this.roomService.createRoom(roomDto);
            return new ResponseEntity<ApiResponse>(new ApiResponse("Room create successful!",room,true), HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<ApiResponse>(new ApiResponse("Room create failed!",false), HttpStatus.OK);
        }

    }

    @GetMapping("/getAll")
    public ResponseEntity<List<RoomDto>> getAllRoom(
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "true") boolean isAscending
            ){
        Sort sort = isAscending ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        List<RoomDto> allRoom = this.roomService.getAllRoom(sort);
        return new ResponseEntity<List<RoomDto>>(allRoom,HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<RoomDto> getSingleRoom(@PathVariable Long id){
        RoomDto singleRoom = this.roomService.getSingleRoom(id);
        return new ResponseEntity<RoomDto>(singleRoom,HttpStatus.OK);
    }

    @PutMapping("/updated/{id}")
    public ResponseEntity<ApiResponse> updateRoom(@RequestBody RoomDto roomDto,@PathVariable Long id){
        try {
            RoomDto room = this.roomService.updateRoom(roomDto,id);
            return new ResponseEntity<ApiResponse>(new ApiResponse("Room update successful!",room,true), HttpStatus.CREATED);
        }catch (Exception ex){
            return new ResponseEntity<ApiResponse>(new ApiResponse("Room update failed!",false), HttpStatus.OK);
        }

    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ApiResponse> deleteRoom(@PathVariable Long id){
        this.roomService.deleteRoom(id);
        return new ResponseEntity<ApiResponse>(new ApiResponse("Room is deleted successfully !!",true),HttpStatus.OK);
    }

}
