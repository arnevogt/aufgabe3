package hsbo.kswe.aufgabe3;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;

@WebServlet(value = "/weather", name = "weatherServlet")
public class WeatherServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        OpenWeatherMap owm = new OpenWeatherMap("insert your api key here");

        //not checking if city parameter is specified!
        CurrentWeather cwd = owm.currentWeatherByCityName(req.getParameter("city"));

        String city = cwd.getCityName();
        String baseStation = cwd.getBaseStation();
        float temperature = cwd.getMainInstance().getTemperature();

        resp.setStatus(200);
        resp.setHeader("Content-Type", "application/json");
        resp.getWriter().print("{\"city\": \"" + city + "\","
                + "\"baseStation\": \"" + baseStation + "\","
                + "\"temperature\": \"" + temperature + "\""
                + "}");

    }

}
