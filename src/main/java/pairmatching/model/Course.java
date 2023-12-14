package pairmatching.model;

public enum Course {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private String name;

    Course(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Course findByCourse(String input) {
        for (Course course : Course.values()) {
            if (course.getName().equalsIgnoreCase(input)) {
                return course;
            }
        }
        throw new IllegalArgumentException("No constant with text " + input + " found");
    }
}
