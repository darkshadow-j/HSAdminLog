package pl.pawel.hslogs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.pawel.hslogs.httpclient;
import pl.pawel.hslogs.jpa.LogsDAO;
import pl.pawel.hslogs.model.LogsModel;

import java.sql.Date;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/logs")
public class LogsController {


    @Autowired
    private LogsDAO logsDAO;
    @Autowired
    private pl.pawel.hslogs.httpclient httpclient;


    @GetMapping
    public List<LogsModel> getAllLogs() throws ParseException {
        List<LogsModel> logsModels = logsDAO.getLogsModelByDateAndTimeBetweenAndProgram("2020-04-23", "08:00:00", "18:00:00", "firewall,info");
        logsModels.forEach(p -> {
            LogsModel ls = logsDAO.getFirstByDateAndTimeBeforeAndMessageIsContainingAndMessageIsContaining(p.getDate(), p.getTime(), p.getIp(), "logged in");
            if (ls != null) {
                ls.setLogsModel(p);
                ls.setTelephone(httpclient.getTelNumberByName(ls.getUsername()));
                System.out.println(ls);
            }

        });

        return logsDAO.getLogsModelByDateAndTimeBetweenAndProgram("2020-04-23", "08:00:00", "18:00:00", "firewall,info");
    }

    @GetMapping("/log")
    public List<LogsModel> getAllLogss() throws ParseException {

        return logsDAO.getLogsModelByMessageIsContainingAndMessageIsContaining("20.20", "logged in");
    }


}
