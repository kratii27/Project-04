package in.co.rays.proj4.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.HostelRoomAllocationBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.exception.DuplicateRecordException;
import in.co.rays.proj4.model.HostelRoomAllocationModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.DataValidator;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "HostelRoomAllocationCtl", urlPatterns = { "/ctl/HostelRoomAllocationCtl" })
public class HostelRoomAllocationCtl extends BaseCtl {

    @Override
    protected void preload(HttpServletRequest request) {
        // No preload required
    }

    @Override
    protected boolean validate(HttpServletRequest request) {

        boolean pass = true;

        if (DataValidator.isNull(request.getParameter("studentName"))) {
            request.setAttribute("studentName", PropertyReader.getValue("error.require", "Student Name"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("roomNumber"))) {
            request.setAttribute("roomNumber", PropertyReader.getValue("error.require", "Room Number"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("blockName"))) {
            request.setAttribute("blockName", PropertyReader.getValue("error.require", "Block Name"));
            pass = false;
        }
        if (DataValidator.isNull(request.getParameter("allotmentDate"))) {
            request.setAttribute("allotmentDate", PropertyReader.getValue("error.require", "Allotment Date"));
            pass = false;
        }

        return pass;
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {

        HostelRoomAllocationBean bean = new HostelRoomAllocationBean();

        bean.setId(DataUtility.getLong(request.getParameter("id")));
        bean.setStudentName(DataUtility.getString(request.getParameter("studentName")));
        bean.setRoomNumber(DataUtility.getLong(request.getParameter("roomNumber")));
        bean.setBlockName(DataUtility.getString(request.getParameter("blockName")));

        String allotmentDate = request.getParameter("allotmentDate");
        if (allotmentDate != null && !allotmentDate.isEmpty()) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                bean.setAllotmentDate(sdf.parse(allotmentDate));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        populateDTO(bean, request);
        return bean;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        long id = DataUtility.getLong(request.getParameter("id"));
        HostelRoomAllocationModel model = new HostelRoomAllocationModel();

        if (id > 0) {
            try {
                HostelRoomAllocationBean bean = model.findByPk(id);
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
        HostelRoomAllocationModel model = new HostelRoomAllocationModel();
        long id = DataUtility.getLong(request.getParameter("id"));

        if (OP_SAVE.equalsIgnoreCase(op)) {
            HostelRoomAllocationBean bean = (HostelRoomAllocationBean) populateBean(request);
            try {
                model.add(bean);
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Hostel Room Allocation added successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Room Number already allocated", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_UPDATE.equalsIgnoreCase(op)) {
            HostelRoomAllocationBean bean = (HostelRoomAllocationBean) populateBean(request);
            try {
                if (id > 0) {
                    model.update(bean);
                }
                ServletUtility.setBean(bean, request);
                ServletUtility.setSuccessMessage("Hostel Room Allocation updated successfully", request);
            } catch (DuplicateRecordException e) {
                ServletUtility.setBean(bean, request);
                ServletUtility.setErrorMessage("Room Number already allocated", request);
            } catch (ApplicationException e) {
                e.printStackTrace();
                ServletUtility.handleException(e, request, response);
                return;
            }

        } else if (OP_CANCEL.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.HOSTEL_ROOM_ALLOCATION_LIST_CTL, request, response);
            return;

        } else if (OP_RESET.equalsIgnoreCase(op)) {
            ServletUtility.redirect(ORSView.HOSTEL_ROOM_ALLOCATION_CTL, request, response);
            return;
        }

        ServletUtility.forward(getView(), request, response);
    }

    @Override
    protected String getView() {
        return ORSView.HOSTEL_ROOM_ALLOCATION_VIEW;
    }
}