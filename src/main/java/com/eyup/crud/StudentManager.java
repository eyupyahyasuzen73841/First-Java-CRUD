
package com.eyup.crud;

import java.util.ArrayList;
import java.util.List;

public class StudentManager {

    private final List<Student> students = new ArrayList<>();

    // CREATE - Add a student
    public void addStudent(Student student) {
        students.add(student);
        System.out.println("Student added successfully!");
    }

    // READ - List all students
    public void listStudents() {
        if (students.isEmpty()) {
            System.out.println("No students found.");
            return;
        }

        for (Student student : students) {
            System.out.println(student);
        }
    }

    // UPDATE - Update student information
    public void updateStudent(int id, String name, String email) {
        for (Student student : students) {
            if (student.getId() == id) {
                student.setName(name);
                student.setEmail(email);
                System.out.println("Student updated successfully!");
                return;
            }
        }
        System.out.println("Student not found.");
    }

    // DELETE - Remove a student
    public void deleteStudent(int id) {
        boolean removed = students.removeIf(
                student -> student.getId() == id
        );

        if (removed) {
            System.out.println("Student deleted successfully!");
        } else {
            System.out.println("Student not found.");
        }
    }
}
