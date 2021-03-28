package edu.ada.service.library.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.ada.service.library.model.UserBookActivity;
import edu.ada.service.library.repository.UserBookActivityRepository;
import edu.ada.service.library.service.UserBookActivityService;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserBookActivityServiceImpl implements UserBookActivityService {

    @Autowired
    private UserBookActivityRepository userBookActivityRepository;

    @Override
    public void add(UserBookActivity activity) {
        userBookActivityRepository.save(activity);
    }

    @Override
    public List<UserBookActivity> getAllActivities() {
        List<UserBookActivity> activities=new ArrayList<>();
        userBookActivityRepository.findAll().forEach(activities::add);
        return activities;
    }
}
