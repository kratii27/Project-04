package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.PortfolioBean;
import in.co.rays.proj4.bean.UserBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.PortfolioModel;
import in.co.rays.proj4.model.UserModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "PortfolioCtl", urlPatterns = { "/ctl/PortfolioCtl" })
public class PortfolioCtl extends BaseCtl {

    @Override
    protected void preload(HttpServletRequest request) {

        HashMap map = new HashMap();
        map.put("Equity", "Equity");
        map.put("Debt", "Debt");
        map.put("Hybrid", "Hybrid");
        map.put("Real Estate", "Real Estate");
        map.put("Commodity", "Commodity");
        map.put("Cryptocurrency", "Cryptocurrency");

        request.setAttribute("preload", map);
    }

    @Override
    protected boolean validate(HttpServletRequest request) {

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("portfolioName"))) {
            request.setAttribute("portfolioName", PropertyReader.getValue("error.require", "Portfolio name"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("totalValue"))) {
            request.setAttribute("totalValue", PropertyReader.getValue("error.require", "Total value"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("createdDate"))) {
            request.setAttribute("createdDate", PropertyReader.getValue("error.require", "Created date"));
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        PortfolioBean bean = new PortfolioBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setPortfolioName(DataUtility.getString(request.getParameter("portfolioName")));
        bean.setTotalValue(DataUtility.getString(request.getParameter("totalValue")));
        bean.setCreatedDate(DataUtility.getDate(request.getParameter("createdDate")));
        
        populateDTO(bean, request);

        return bean;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        long id = DataUtility.getLong(request.getParameter("id"));
        PortfolioModel model = new PortfolioModel();

        if (id > 0) {
            try {
                PortfolioBean bean = model.findByPk(id);
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
        PortfolioModel model = new PortfolioModel();
        long id = DataUtility.getLong(request.getParameter("id"));
        
        if (OP_SAVE.equalsIgnoreCase(op)) {
            PortfolioBean bean = (PortfolioBean) populateBean(request);
            try {
                model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Portfolio added successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Portfolio Name already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            PortfolioBean bean = (PortfolioBean) populateBean(request);
            try {
                if (id > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Portfolio details updated successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Portfolio Name already exists", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.PORTFOLIO_LIST_CTL, request, response);
            return;

        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.PORTFOLIO_CTL, request, response);
            return;
        }

        ServletUtility.forward(getView(), request, response);

    }

    @Override
    protected String getView() {
        return ORSView.PORTFOLIO_VIEW;
    }
}