import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CourseRegistrationSystem {
    // Course Database
    private Map<String, Course> courses;

    // Student Database
    private Map<Integer, Student> students;

    public CourseRegistrationSystem() {
        courses = new HashMap<>();
        students = new HashMap<>();
    }

    // Add a course to the database
    public void addCourse(String courseCode, String title, String description, int capacity, String schedule) {
        Course course = new Course(courseCode, title, description, capacity, schedule);
        courses.put(courseCode, course);
    }

    // Add a student to the database
    public void addStudent(int studentId, String name) {
        Student student = new Student(studentId, name);
        students.put(studentId, student);
    }

    // Display available courses with details and available slots
    public void displayCourseListing() {
        for (Course course : courses.values()) {
            int registeredStudents = 0;
            for (Student student : students.values()) {
                if (student.isRegisteredForCourse(course.getCourseCode())) {
                    registeredStudents++;
                }
            }
            int availableSlots = course.getCapacity() - registeredStudents;
            System.out.println("Course Code: " + course.getCourseCode());
            System.out.println("Title: " + course.getTitle());
            System.out.println("Description: " + course.getDescription());
            System.out.println("Schedule: " + course.getSchedule());
            System.out.println("Available Slots: " + availableSlots);
            System.out.println();
        }
    }

    // Allow a student to register for a course
    public void registerStudentForCourse(int studentId, String courseCode) {
        Student student = students.get(studentId);
        Course course = courses.get(courseCode);
        if (course != null && student != null) {
            if (course.getCapacity() > student.getRegisteredCourses().size()) {
                student.addRegisteredCourse(courseCode);
                System.out.println("Student " + student.getName() + " registered for course " + courseCode);
            } else {
                System.out.println("Course " + courseCode + " is full");
            }
        } else {
            System.out.println("Student or course not found");
        }
    }

    // Allow a student to drop a course
    public void dropCourseForStudent(int studentId, String courseCode) {
        Student student = students.get(studentId);
        if (student != null) {
            student.removeRegisteredCourse(courseCode);
            System.out.println("Student " + student.getName() + " dropped course " + courseCode);
        } else {
            System.out.println("Student not found");
        }
    }

    // Inner class for Course
    private static class Course {
        private String courseCode;
        private String title;
        private String description;
        private int capacity;
        private String schedule;

        public Course(String courseCode, String title, String description, int capacity, String schedule) {
            this.courseCode = courseCode;
            this.title = title;
            this.description = description;
            this.capacity = capacity;
            this.schedule = schedule;
        }

        public String getCourseCode() {
            return courseCode;
        }

        public String getTitle() {
            return title;
        }

        public String getDescription() {
            return description;
        }

        public int getCapacity() {
            return capacity;
        }

        public String getSchedule() {
            return schedule;
        }
    }

    // Inner class for Student
    private static class Student {
        private int studentId;
        private String name;
        private List<String> registeredCourses;

        public Student(int studentId, String name) {
            this.studentId = studentId;
            this.name = name;
            this.registeredCourses = new ArrayList<>();
        }

        public int getStudentId() {
            return studentId;
        }

        public String getName() {
            return name;
        }

        public List<String> getRegisteredCourses() {
            return registeredCourses;
        }

        public void addRegisteredCourse(String courseCode) {
            registeredCourses.add(courseCode);
        }

        public void removeRegisteredCourse(String courseCode) {
            registeredCourses.remove(courseCode);
        }

        public boolean isRegisteredForCourse(String courseCode) {
            return registeredCourses.contains(courseCode);
        }
    }

    public static void main(String[] args) {
        CourseRegistrationSystem system = new CourseRegistrationSystem();

        // Add courses
        system.addCourse("CS101", "Introduction to Computer Science", "Intro to CS concepts", 30, "MWF 10:00-11:30");
    
    }
}