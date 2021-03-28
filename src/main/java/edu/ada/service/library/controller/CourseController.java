package edu.ada.service.library.controller;

import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import edu.ada.service.library.model.Course;
import edu.ada.service.library.service.CoursesService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("api/courses")
public class CourseController {

    @Autowired
    private CoursesService coursesService;

    @GetMapping
    public @ResponseBody ResponseEntity<Object> getCourses() {
        List<Course> listCourses = coursesService.getAllCourses();

        List<JSONObject> courses = new ArrayList<JSONObject>();

        for (Course course: listCourses) {
            JSONObject c = new JSONObject();
            c.put("id", course.getId());
            c.put("name", course.getName());

            courses.add(c);
        }
        return new ResponseEntity<Object>(courses, HttpStatus.OK);

    }
}
