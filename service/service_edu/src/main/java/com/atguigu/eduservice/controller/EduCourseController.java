package com.atguigu.eduservice.controller;


import com.atguigu.commonutils.R;
import com.atguigu.eduservice.entity.EduCourse;
import com.atguigu.eduservice.entity.vo.CourseInfoVo;
import com.atguigu.eduservice.entity.vo.CoursePublishVo;
import com.atguigu.eduservice.service.EduCourseService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 课程 前端控制器
 * </p>
 *
 * @author testjava
 * @since 2022-11-10
 */
@RestController
@RequestMapping("/eduservice/course")
@CrossOrigin
public class EduCourseController {
    @Autowired
    private EduCourseService courseService;

    @GetMapping
    public R getCourseList(){
        List<EduCourse> list = courseService.list(null);
        return R.ok().data("list",list);
    }

    @PostMapping("addCourseInfo")
    public R addCourseInfo(@RequestBody CourseInfoVo courseInfoVo) {
        String id = courseService.saveCourseInfo(courseInfoVo);
        return R.ok().data("courseId", id);
    }

    @ApiOperation(value = "根据ID查询课程")
    @GetMapping("getCourseInfo/{courseId}")
    public R getById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String courseId) {
        CourseInfoVo courseInfoVo = courseService.getCourseInfo(courseId);
        return R.ok().data("courseInfoVo", courseInfoVo);
    }

    @ApiOperation(value = "更新课程")
    @PostMapping("updateCourseInfo")
    public R updateCourseInfo(
            @ApiParam(name = "CourseInfoVo", value = "课程基本信息", required = true)
            @RequestBody CourseInfoVo courseInfoVo) {
        courseService.updateCourseInfo(courseInfoVo);
        return R.ok();
    }

    @ApiOperation(value = "根据ID获取课程发布信息")
    @GetMapping("getPublishCourseInfo/{id}")
    public R getCoursePublishVoById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        CoursePublishVo courseInfoVo = courseService.getCoursePublishVo(id);
        return R.ok().data("publishCourse", courseInfoVo);
    }

    @ApiOperation(value = "根据id发布课程")
    @PostMapping("publishCourse/{id}")
    public R publishCourse(
            @ApiParam(name = "id", value = "课程ID", required = true)
        @PathVariable String id){
        EduCourse eduCourse = new EduCourse();
        eduCourse.setId(id);
        eduCourse.setStatus("Normal");
        courseService.updateById(eduCourse);
        return R.ok();
    }

    @ApiOperation(value = "根据ID删除课程")
    @DeleteMapping("{id}")
    public R removeById(
            @ApiParam(name = "id", value = "课程ID", required = true)
            @PathVariable String id){
        courseService.removeCourse(id);
        return R.ok();
    }


}

