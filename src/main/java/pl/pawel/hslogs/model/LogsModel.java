package pl.pawel.hslogs.model;


import javax.persistence.*;
import javax.xml.crypto.Data;
import java.sql.Time;
import java.util.Date;

@Entity(name = "log")
public class LogsModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String date;
    private String time;
    private String message;
    private String program;
    @Transient
    String ip;
    @Transient
    String username;

    public String getIp() {
        this.setIp();
        return ip;
    }

    public void setIp() {
        String r = this.message;
        r = r.substring(0, r.lastIndexOf(">"));
        r = r.substring(0, r.lastIndexOf(":"));
        r = r.substring(r.lastIndexOf(",") + 1).trim();
        this.ip = r;
    }

    public String getUsername() {
        return username;
    }

    public LogsModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getProgram() {
        return program;
    }

    public void setProgram(String program) {
        this.program = program;
    }
}

