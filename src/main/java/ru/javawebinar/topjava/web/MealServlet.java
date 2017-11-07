package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;


public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);
    List<MealWithExceed> list=MealsUtil.getMealsWithExceeded();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       // resp.sendRedirect("meal.jsp");

        //String reqParameter=req.getParameter("action");

        //List<MealWithExceed> list = MealsUtil.getMealsWithExceeded();
        //List<Meal> list = MealsUtil.getMeals();
        String action = req.getParameter("action");

        //List<MealWithExceed> list = new ArrayList<>();
        if (action==null)
        {

            req.setAttribute("meal",list);
            req.getRequestDispatcher("/meal.jsp").forward(req, resp);
            return;
        }
          //  req.setAttribute("meal",list);
        switch (action) {

            case "delete":
                String id = req.getParameter("id");
                int i=Integer.parseInt(id);
                list.remove(i-1);
                resp.sendRedirect("meal");
                return;
            case "edit":

                break;



        }


    }


}
