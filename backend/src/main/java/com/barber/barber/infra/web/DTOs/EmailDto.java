package com.barber.barber.infra.web.DTOs;

public class EmailDto {
    String email;
    String subject;

    public EmailDto(String email, String subject) {
        this.email = email;
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
