package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static Logger log = getLogger(MealServlet.class);
    List<MealWithExceed> list=MealsUtil.getMealsWithExceeded();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("doGet MealServlet");
        String action = req.getParameter("action");
        if (action==null)
        {
            req.setAttribute("meal",list);
            req.getRequestDispatcher("/meal.jsp").forward(req, resp);
            return;
        }
            req.setAttribute("meal",list);
        switch (action) {
            case "delete":
                String id = req.getParameter("id");
                int i=Integer.parseInt(id);
                list.remove(i);
                resp.sendRedirect("meal");
                return;
            case "edit":
            case "add":
                req.setAttribute("meal",list);
                req.getRequestDispatcher("/edit.jsp").forward(req, resp);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.debug("doPost MealServlet");
        req.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        //String id = req.getParameter("id");

        switch (action)
        {
            case "edit":
            case "add":
                String date = req.getParameter("date");
                String time = req.getParameter("time");
                String str=date+" "+time;
                String description = req.getParameter("description");
                String calories = req.getParameter("calories");
                String exceed = req.getParameter("exceed");
                if (date==null || time==null || description==null || calories==null || exceed==null)
                {
                    resp.sendRedirect("meal");
                }
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                LocalDateTime dateTime = LocalDateTime.parse(str, formatter);
                list.add(new MealWithExceed(dateTime,description,Integer.parseInt(calories),Boolean.parseBoolean(exceed)));
                break;
        }
        resp.sendRedirect("meal");
    }
}
