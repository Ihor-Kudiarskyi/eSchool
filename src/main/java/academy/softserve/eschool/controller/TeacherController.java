package academy.softserve.eschool.controller;

import academy.softserve.eschool.converter.TeacherNamesDTOConverter;
import academy.softserve.eschool.dto.EditTeacherDTO;
import academy.softserve.eschool.dto.TeacherDTO;
import academy.softserve.eschool.dto.TeacherNamesDTO;
import academy.softserve.eschool.repository.ClassRepository;
import academy.softserve.eschool.repository.TeacherRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/teachers")
@Api(description = "Teachers controller")
public class TeacherController {

    @Autowired
    private TeacherRepository teacherRepository;

    @GetMapping("")
    @ApiOperation(value = "Get list of teacher(only id and names)")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    public List<TeacherNamesDTO> getall(){
        return TeacherNamesDTOConverter.convertList(teacherRepository.findAll());
    }
  
    @PostMapping
    @ApiOperation(value = "Add teacher, first name and last name passed in html")
    @ApiResponses(
            value={
                    @ApiResponse(code = 201, message = "teacher crated"),
                    @ApiResponse(code = 500, message = "server error")
            }
    )
    public TeacherDTO addTeacher(@RequestBody TeacherDTO teacher) {
        return teacher;
    }
  
    @ApiOperation(value = "Get all info about teacher")
    @GetMapping("/{id}")
    @ApiResponses(
            value = {
                    @ApiResponse(code = 200, message = "OK"),
                    @ApiResponse(code = 500, message = "Serever error")
            }
    )
    @SneakyThrows
    public TeacherDTO getTeacher(@PathVariable int id){
        //SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-mm-dd");
        return TeacherNamesDTOConverter.convertOne(teacherRepository.findById(id).get());
    }
    @PutMapping("/{id}")
    @ApiOperation(value = "update profile of teacher")
    @ApiResponses(
            value = {
                    @ApiResponse( code = 201 , message = "Successfully created"),
                    @ApiResponse( code = 400, message = "Bad data"),
                    @ApiResponse(code = 500, message = "Server error")
            }
    )
    public void updateTeacher(@RequestBody EditTeacherDTO teacher, @PathVariable int id){
        // someservice.update(id,teacher)
    }



}

