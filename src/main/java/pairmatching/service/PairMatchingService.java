package pairmatching.service;

import pairmatching.model.Course;
import pairmatching.model.Crew;
import pairmatching.response.CreateCrewResponse;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class PairMatchingService {

    public static final String BACKEND_CREW = "/Users/chw/Desktop/project/java-pairmatching-precourse/src/main/resources/backend-crew.md";
    public static final String FRONTEND_CREW = "/Users/chw/Desktop/project/java-pairmatching-precourse/src/main/resources/frontend-crew.md";

    public CreateCrewResponse createCrews() throws IOException {
        List<Crew> backend = new ArrayList<>();
        List<Crew> frontend = new ArrayList<>();

        String[] backendCrewNames = new String(getFileString(BACKEND_CREW)).split("\n");
        String[] frontendCrewNames = new String(getFileString(FRONTEND_CREW)).split("\n");

        saveCrews(backend, backendCrewNames, Course.BACKEND);
        saveCrews(frontend, frontendCrewNames, Course.FRONTEND);

        return CreateCrewResponse.of(backend, frontend);
    }

    private static void saveCrews(List<Crew> backend, String[] backendCrewNames, Course course) {
        for (String name : backendCrewNames) {
            Crew crew = Crew.createCrew(course, name);
            backend.add(crew);
        }
    }

    private static byte[] getFileString(String path) throws IOException {
        return Files.readAllBytes(Paths.get(path));
    }
}
