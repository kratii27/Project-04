package in.co.rays.proj4.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.bean.HostelRoomAllocationBean;
import in.co.rays.proj4.exception.ApplicationException;
import in.co.rays.proj4.model.HostelRoomAllocationModel;
import in.co.rays.proj4.util.DataUtility;
import in.co.rays.proj4.util.PropertyReader;
import in.co.rays.proj4.util.ServletUtility;

@WebServlet(name = "HostelRoomAllocationListCtl", urlPatterns = { "/ctl/HostelRoomAllocationListCtl" })
public class HostelRoomAllocationListCtl extends BaseCtl {

    @Override
    protected void preload(HttpServletRequest request) {
        // No preload required
    }

    @Override
    protected BaseBean populateBean(HttpServletRequest request) {
        HostelRoomAllocationBean bean = new HostelRoomAllocationBean();

        bean.setStudentName(DataUtility.getString(request.getParameter("studentName")));
        bean.setBlockName(DataUtility.getString(request.getParameter("blockName")));

        return bean;
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int pageNo = 1;
        int pageSize = DataUtility.getInt(PropertyReader.getValue("page.size"));

        HostelRoomAllocationBean bean = (HostelRoomAllocationBean) populateBean(request);
        HostelRoomAllocationModel model = new HostelRoomAllocationModel();

        try {
            List<HostelRoomAllocationBean> list = model.search(bean, pageNo, pageSize);
            List<HostelRoomAllocationBean> next = model.search(bean, pageNo + 1, pageSize);

            if (list == null || list.isEmpty()) {
                ServletUtility.setErrorMessage("No record found", request);
            }

            ServletUtility.setList(list, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.setBean(bean, request);
            request.setAttribute("nextListSize", next.size());

            ServletUtility.forward(getView(), request, response);

        } catch (ApplicationException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List list = null;
        List next = null;

        int pageNo = DataUtility.getInt(request.getParameter("pageNo"));
        int pageSize = DataUtility.getInt(request.getParameter("pageSize"));

        pageNo = (pageNo == 0) ? 1 : pageNo;
        pageSize = (pageSize == 0) ? DataUtility.getInt(PropertyReader.getValue("page.size")) : pageSize;

        HostelRoomAllocationBean bean = (HostelRoomAllocationBean) populateBean(request);
        HostelRoomAllocationModel model = new HostelRoomAllocationModel();

        String op = DataUtility.getString(request.getParameter("operation"));
        String[] ids = request.getParameterValues("ids");

        try {

            if (OP_SEARCH.equalsIgnoreCase(op) || "Next".equalsIgnoreCase(op) || "Previous".equalsIgnoreCase(op)) {

                if (OP_SEARCH.equalsIgnoreCase(op)) {
                    pageNo = 1;
                } else if (OP_NEXT.equalsIgnoreCase(op)) {
                    pageNo++;
                } else if (OP_PREVIOUS.equalsIgnoreCase(op) && pageNo > 1) {
                    pageNo--;
                }

            } else if (OP_NEW.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.HOSTEL_ROOM_ALLOCATION_CTL, request, response);
                return;

            } else if (OP_DELETE.equalsIgnoreCase(op)) {
                pageNo = 1;
                if (ids != null && ids.length > 0) {
                    HostelRoomAllocationBean deleteBean = new HostelRoomAllocationBean();
                    for (String id : ids) {
                        deleteBean.setId(DataUtility.getInt(id));
                        model.delete(deleteBean);
                        ServletUtility.setSuccessMessage("Hostel Room Allocation deleted successfully", request);
                    }
                } else {
                    ServletUtility.setErrorMessage("Select at least one record", request);
                }

            } else if (OP_RESET.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.HOSTEL_ROOM_ALLOCATION_LIST_CTL, request, response);
                return;

            } else if (OP_BACK.equalsIgnoreCase(op)) {
                ServletUtility.redirect(ORSView.HOSTEL_ROOM_ALLOCATION_LIST_CTL, request, response);
                return;
            }

            list = model.search(bean, pageNo, pageSize);
            next = model.search(bean, pageNo + 1, pageSize);

            if (list == null || list.size() == 0) {
                ServletUtility.setErrorMessage("No record found", request);
            }

            ServletUtility.setList(list, request);
            ServletUtility.setPageNo(pageNo, request);
            ServletUtility.setPageSize(pageSize, request);
            ServletUtility.setBean(bean, request);
            request.setAttribute("nextListSize", next.size());

            ServletUtility.forward(getView(), request, response);

        } catch (ApplicationException e) {
            e.printStackTrace();
            return;
        }
    }

    @Override
    protected String getView() {
        return ORSView.HOSTEL_ROOM_ALLOCATION_LIST_VIEW;
    }
}