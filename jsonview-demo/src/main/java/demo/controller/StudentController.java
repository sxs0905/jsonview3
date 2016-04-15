package demo.controller;

import java.util.List;

import javax.annotation.Resource;

import org.developframework.jsonview.springmvc.res.JsonviewEmptyResponse;
import org.developframework.jsonview.springmvc.res.JsonviewResponse;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import demo.service.StudentService;
import demo.service.domain.Student;

@RestController
@RequestMapping("students")
public class StudentController {

	@Resource
	private StudentService studentService;

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public JsonviewResponse studentDetail(@PathVariable("id") int id) {
		Student student = studentService.queryStudentDetail(id);
		return new JsonviewEmptyResponse("jsonview-student", "student-detail").putData("student", student);
	}

	@RequestMapping(value = "{id}", method = RequestMethod.GET)
	public Student studentDetail2(@PathVariable("id") int id) {
		Student student = studentService.queryStudentDetail(id);
		return student;
	}

	@RequestMapping(method = RequestMethod.GET)
	public JsonviewResponse studentList() {
		List<Student> students = studentService.queryStudentList();
		return new JsonviewEmptyResponse("jsonview-student", "student-list-view").putData("students", students);
	}
}
