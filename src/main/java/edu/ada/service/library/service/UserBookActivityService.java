package edu.ada.service.library.service;

import edu.ada.service.library.model.UserBookActivity;

import java.util.List;

public interface UserBookActivityService {
    public void add(UserBookActivity activity);

    public List<UserBookActivity> getAllActivities();
}
