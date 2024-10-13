package googlecalendar;


import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;

import java.io.IOException;
import java.text.SimpleDateFormat;

public class GoogleCalendarTools {

    private Calendar service;
    private final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public GoogleCalendarTools(Calendar service) {
        this.service = service;
    }

    public void addCalendar(String name) throws IOException {

        com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
        calendar.setSummary(name);
        calendar.setTimeZone("America/New_York");

        com.google.api.services.calendar.model.Calendar createdCalendar = service.calendars().insert(calendar).execute();

        System.out.println(createdCalendar.getId());
        System.out.println(createdCalendar.getSummary());

    }

    public void addEvent(String calendarId, String name, String date, String location) throws IOException {
        Event newEvent = new Event().setSummary(name).setLocation(location);

        //newEvent.setStart(new EventDateTime().setDate(new DateTime(dateFormat.format(new Date()))).setTimeZone("America/New_York"));

        newEvent.setStart(new EventDateTime().setDate(new DateTime(date)).setTimeZone("America/New_York"));

        newEvent.setEnd(new EventDateTime().setDate(new DateTime(date)).setTimeZone("America/New_York"));

        newEvent = service.events().insert(calendarId, newEvent).execute();

        System.out.println(newEvent.getHtmlLink());

    }

    public void addEvent(String calendarId, String name, String date) throws IOException {
        Event newEvent = new Event().setSummary(name);


        newEvent.setStart(new EventDateTime().setDate(new DateTime(date)).setTimeZone("America/New_York"));

        newEvent.setEnd(new EventDateTime().setDate(new DateTime(date)).setTimeZone("America/New_York"));

        newEvent = service.events().insert(calendarId, newEvent).execute();

        System.out.println(newEvent.getHtmlLink());

    }
}
