package me;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {

    public Map<String, Session> sessions = new HashMap<>();

    public void add(String username, Session session) {
        sessions.put(username, session);
    }



}
