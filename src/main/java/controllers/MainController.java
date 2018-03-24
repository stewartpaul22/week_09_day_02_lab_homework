package controllers;

import db.Seeds;

import static spark.SparkBase.staticFileLocation;

public class MainController {

    public static void main(String[] args) {

        staticFileLocation("/public");

        ManagersController managersController = new ManagersController();
        EngineersController engineersController = new EngineersController();
        DepartmentsController departmentsController = new DepartmentsController();
        EmployeesController employeesController = new EmployeesController();

        Seeds.seedData();


    }

}
