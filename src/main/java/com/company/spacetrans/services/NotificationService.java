package com.company.spacetrans.services;

import io.jmix.ui.Notifications;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class NotificationService {


    public void showMessage(String message)
    {

//        notifications.create().withCaption("Event")
//                .withDescription(message)
//                .withType(Notifications.NotificationType.TRAY)
//                .show();
    }

}
