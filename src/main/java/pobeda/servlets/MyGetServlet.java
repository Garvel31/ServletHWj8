package pobeda.servlets;


import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import pobeda.Car;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


@WebServlet(urlPatterns = {"/first"})
public class MyGetServlet extends HttpServlet {

    private final Gson gson = new Gson();
    private final static String filePath = "cars.json";


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {

        try {
            JsonReader reader = new JsonReader(new FileReader(filePath));
            Car car = gson.fromJson(reader, Car.class);
            PrintWriter out = resp.getWriter();
            resp.setContentType("application/json");
            resp.setCharacterEncoding("UTF-8");
            reader.close();
            out.print(car.toString());
            out.flush();
        } catch (FileNotFoundException ex) {
            PrintWriter out = resp.getWriter();
            resp.setContentType("text/html");
            resp.setCharacterEncoding("UTF-8");
            out.print("file not found");
            out.flush();
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Car car = new Car("Yellow", "Porsche");

        try {
            Writer writer = new FileWriter(filePath);
            new Gson().toJson(car, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");
        Car car = new Car("Red", "BMW");

        try {
            Writer writer = new FileWriter(filePath);
            new Gson().toJson(car, writer);
            writer.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        File file = new File("E:\\java\\projects\\IBS\\cars.json");
        file.delete();
    }
}
