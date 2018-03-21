package controllers;

import db.DBHelper;
import models.Department;
import models.Employee;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static spark.Spark.get;
import static spark.Spark.post;

public class DepartmentsController {

    public DepartmentsController() {
        this.setupEndpoints();
    }

    private void setupEndpoints() {

        get("/departments", (req, res) -> {

            List<Department> departments = DBHelper.getAll(Department.class);
            List<Employee> employees = DBHelper.getAll(Employee.class);
            employees.get(0).getDepartment().getId();

            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/departments/index.vtl");
            model.put("departments", departments);
            model.put("employees", employees);

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        get("/departments/new", (req, res) -> {

            Map<String, Object> model = new HashMap<>();
            model.put("template", "templates/departments/create.vtl");

            return new ModelAndView(model, "templates/layout.vtl");

        }, new VelocityTemplateEngine());

        post("/departments", (req, res) -> {

            String title = req.queryParams("title");

            Department department = new Department(title);

            DBHelper.save(department);

            res.redirect("/departments");

            return null;

        }, new VelocityTemplateEngine());

    }



}
