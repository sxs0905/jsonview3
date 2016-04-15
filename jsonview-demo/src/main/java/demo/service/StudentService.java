package demo.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import demo.service.domain.Student;

@Service
public class StudentService {

	private Map<Integer, Student> database = new HashMap<>();

	public StudentService() {
		this.database.put(1, new Student(1, "Peter", 1, "2016-01-01"));
		this.database.put(2, new Student(2, "Tom", 2, "2016-02-02"));
		this.database.put(3, new Student(3, "Lucy", 2, "2016-03-03"));
		this.database.put(4, new Student(4, "Bob", 3, "2016-04-04"));
		this.database.put(5, new Student(5, "Bill", 3, "2016-05-05"));
	}

	public Student queryStudentDetail(int id) {
		return database.get(id);
	}

	public List<Student> queryStudentList() {
		List<Student> list = new ArrayList<>(database.size());
		list.addAll(database.values());
		Collections.sort(list, (o1, o2) -> {
			if (o1.getId() > o2.getId()) {
				return 1;
			} else if (o1.getId() < o2.getId()) {
				return -1;
			}
			return 0;
		});
		return list;
	}
}
