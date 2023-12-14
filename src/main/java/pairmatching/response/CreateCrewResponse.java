package pairmatching.response;

import pairmatching.model.Crew;

import java.util.List;

public class CreateCrewResponse {
    private final List<Crew> backend;
    private final List<Crew> frontend;

    private CreateCrewResponse(List<Crew> backend, List<Crew> frontend) {
        this.backend = backend;
        this.frontend = frontend;
    }

    public static CreateCrewResponse of(List<Crew> backend, List<Crew> frontend) {
        return new CreateCrewResponse(backend, frontend);
    }
}
