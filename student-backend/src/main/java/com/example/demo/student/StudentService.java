package com.example.demo.student;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	@Autowired
	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

  public List<Student> getStudents(){
		return studentRepository.findAll();
	}

	public void addNewStudent(Student student) {
		Optional<Student> s = studentRepository.findStudentByEmail(student.getEmail());
		if(s.isPresent()){
			throw new IllegalStateException("email taken");
		}
		studentRepository.save(student);
	}

	public void deleteStudent(Long id) {
		if (!studentRepository.existsById(id)){
			throw new IllegalStateException("can't delete nonexistent student");
		}
		studentRepository.deleteById(id);
	}

	@Transactional
	public void updateStudent(Long id, String name, String email) {
		Student s = studentRepository.findById(id)
			.orElseThrow(() -> new IllegalStateException(
				"can't update nonexistent student"));

		
		if (studentRepository.findStudentByEmail(email).isPresent()){
			throw new IllegalStateException("email taken");
		}
		if(name != null && name.length() > 0){
			s.setName(name);
		}
		if(
			email != null 
			&& email.length() > 0
			&& !studentRepository.findStudentByEmail(email).isPresent()
		){
			s.setEmail(email);}
	}

}
