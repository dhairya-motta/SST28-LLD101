package core;

import enums.Direction;
import enums.RequestType;

public class Request {
    private static int idCounter = 1;
    private final int requestId;
    private final int floor;
    private final Direction direction;
    private final RequestType requestType;

    public Request(int floor, Direction direction, RequestType requestType) {
        this.requestId = idCounter++;
        this.floor = floor;
        this.direction = direction;
        this.requestType = requestType;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getFloor() {
        return floor;
    }

    public Direction getDirection() {
        return direction;
    }

    public RequestType getRequestType() {
        return requestType;
    }

    @Override
    public String toString() {
        return "Request{id=" + requestId + ", floor=" + floor + ", direction=" + direction + ", type=" + requestType + "}";
    }
}
