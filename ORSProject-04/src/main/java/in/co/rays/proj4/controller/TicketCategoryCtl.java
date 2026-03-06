package in.co.rays.proj4.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.TicketCategoryBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.TicketCategoryModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "TicketCategoryCtl", urlPatterns = { "/ctl/TicketCategoryCtl" })
public class TicketCategoryCtl extends BaseCtl {

    @Override
    protected void preload(HttpServletRequest request) {
        // No preload required
    }

    @Override
    protected boolean validate(HttpServletRequest request) {

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("categoryName"))) {
            request.setAttribute("categoryName", PropertyReader.getValue("error.require", "Category Name"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("price"))) {
            request.setAttribute("price", PropertyReader.getValue("error.require", "Price"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("availableSeats"))) {
            request.setAttribute("availableSeats", PropertyReader.getValue("error.require", "Available Seats"));
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

        TicketCategoryBean bean = new TicketCategoryBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setCategoryName(DataUtility.getString(request.getParameter("categoryName")));
        bean.setPrice(DataUtility.getLong(request.getParameter("price")));
        bean.setAvailableSeats(DataUtility.getInt(request.getParameter("availableSeats")));

        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = DataUtility.getLong(request.getParameter("id"));
        TicketCategoryModel model = new TicketCategoryModel();

        if (id > 0) {
            try {
                TicketCategoryBean bean = model.findByPk(id);
                ServletUtility.setBean(bean, request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, request, response);
                return;
            }
        }
        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String op = request.getParameter("operation");
        TicketCategoryModel model = new TicketCategoryModel();
        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {
            TicketCategoryBean bean = (TicketCategoryBean) populateBean(request);
            try {
                model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Ticket Category added successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Category Name already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            TicketCategoryBean bean = (TicketCategoryBean) populateBean(request);
            try {
                if (id > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Ticket Category updated successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Category Name already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.TICKET_CATEGORY_LIST_CTL, request, response);
            return;

        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.TICKET_CATEGORY_CTL, request, response);
            return;
        }

        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected String getView() {
        return ORSView.TICKET_CATEGORY_VIEW;
    }
}