package au.edu.unsw.infs3634.gamifiedlearning.AsyncTasks;

import java.util.List;

import au.edu.unsw.infs3634.gamifiedlearning.models.User;

public interface AsyncTaskUser {
    void handleInsertUserResult(String result);
    void handleGetUserResult(User user);
    void handleGetAllUsersResult(List<User> users);
    void handleGetUsernamesResult(List<String> usernames);
    void handleGetUserByUserName(User user);
    void handleInsertPoints(String result);
}
