
package com.eyup.crud;

public class Main {
    public static void main(String[] args) {

        StudentManager manager = new StudentManager();

        System.out.println("=== JAVA CRUD APPLICATION ===");

        // CREATE
        System.out.println("\n--- CREATE ---");
        manager.addStudent(new Student(1, "Eyup", "eyup@gmail.com"));
        manager.addStudent(new Student(2, "Saliha", "saliha@gmail.com"));

        // READ
        System.out.println("\n--- READ ---");
        manager.listStudents();

        // UPDATE
        System.out.println("\n--- UPDATE ---");
        manager.updateStudent(1, "Eyup Yahya", "eyup.new@gmail.com");
        manager.listStudents();

        // DELETE
        System.out.println("\n--- DELETE ---");
        manager.deleteStudent(2);
        manager.listStudents();

        System.out.println("\n=== APPLICATION FINISHED ===");
    }
}
