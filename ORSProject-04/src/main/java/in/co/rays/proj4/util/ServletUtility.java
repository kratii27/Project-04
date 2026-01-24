package in.co.rays.proj4.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import in.co.rays.proj4.bean.BaseBean;
import in.co.rays.proj4.controller.BaseCtl;
import in.co.rays.proj4.controller.ORSView;

/**
 * ServletUtility class provides utility methods for Servlet operations.
 * It contains methods for forwarding, redirecting, setting/getting messages,
 * handling beans, lists, pagination, and exceptions in HttpServletRequest/Response.
 * 
 * @author Krati
 */
public class ServletUtility {

    /**
     * Forwards request to a given page.
     * 
     * @param page     the page to forward to
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException
     * @throws ServletException
     */
    public static void forward(String page, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);
    }

    /**
     * Redirects response to a given page.
     * 
     * @param page     the URL to redirect to
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException
     * @throws ServletException
     */
    public static void redirect(String page, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        response.sendRedirect(page);
    }

    /**
     * Gets error message by property from request.
     * 
     * @param property property name
     * @param request  HttpServletRequest object
     * @return the error message or empty string
     */
    public static String getErrorMessage(String property, HttpServletRequest request) {
        String val = (String) request.getAttribute(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Gets message by property from request.
     * 
     * @param property property name
     * @param request  HttpServletRequest object
     * @return message or empty string
     */
    public static String getMessage(String property, HttpServletRequest request) {
        String val = (String) request.getAttribute(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Sets error message in request.
     * 
     * @param msg     the error message
     * @param request HttpServletRequest object
     */
    public static void setErrorMessage(String msg, HttpServletRequest request) {
        request.setAttribute(BaseCtl.MSG_ERROR, msg);
    }

    /**
     * Gets error message from request.
     * 
     * @param request HttpServletRequest object
     * @return error message or empty string
     */
    public static String getErrorMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(BaseCtl.MSG_ERROR);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Sets success message in request.
     * 
     * @param msg     success message
     * @param request HttpServletRequest object
     */
    public static void setSuccessMessage(String msg, HttpServletRequest request) {
        request.setAttribute(BaseCtl.MSG_SUCCESS, msg);
    }

    /**
     * Gets success message from request.
     * 
     * @param request HttpServletRequest object
     * @return success message or empty string
     */
    public static String getSuccessMessage(HttpServletRequest request) {
        String val = (String) request.getAttribute(BaseCtl.MSG_SUCCESS);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Sets a bean in request.
     * 
     * @param bean    BaseBean object
     * @param request HttpServletRequest object
     */
    public static void setBean(BaseBean bean, HttpServletRequest request) {
        request.setAttribute("bean", bean);
    }

    /**
     * Gets a bean from request.
     * 
     * @param request HttpServletRequest object
     * @return BaseBean object or null
     */
    public static BaseBean getBean(HttpServletRequest request) {
        return (BaseBean) request.getAttribute("bean");
    }

    /**
     * Gets a parameter from request.
     * 
     * @param property parameter name
     * @param request  HttpServletRequest object
     * @return parameter value or empty string
     */
    public static String getParameter(String property, HttpServletRequest request) {
        String val = (String) request.getParameter(property);
        if (val == null) {
            return "";
        } else {
            return val;
        }
    }

    /**
     * Sets a list in request.
     * 
     * @param list    list of objects
     * @param request HttpServletRequest object
     */
    public static void setList(List list, HttpServletRequest request) {
        request.setAttribute("list", list);
    }

    /**
     * Gets a list from request.
     * 
     * @param request HttpServletRequest object
     * @return list of objects or null
     */
    public static List getList(HttpServletRequest request) {
        return (List) request.getAttribute("list");
    }

    /**
     * Sets page number in request.
     * 
     * @param pageNo  current page number
     * @param request HttpServletRequest object
     */
    public static void setPageNo(int pageNo, HttpServletRequest request) {
        request.setAttribute("pageNo", pageNo);
    }

    /**
     * Gets page number from request.
     * 
     * @param request HttpServletRequest object
     * @return page number
     */
    public static int getPageNo(HttpServletRequest request) {
        return (Integer) request.getAttribute("pageNo");
    }

    /**
     * Sets page size in request.
     * 
     * @param pageSize page size
     * @param request  HttpServletRequest object
     */
    public static void setPageSize(int pageSize, HttpServletRequest request) {
        request.setAttribute("pageSize", pageSize);
    }

    /**
     * Gets page size from request.
     * 
     * @param request HttpServletRequest object
     * @return page size
     */
    public static int getPageSize(HttpServletRequest request) {
        return (Integer) request.getAttribute("pageSize");
    }

    /**
     * Handles exception by setting it in request and redirecting to error page.
     * 
     * @param e        exception object
     * @param request  HttpServletRequest object
     * @param response HttpServletResponse object
     * @throws IOException
     * @throws ServletException
     */
    public static void handleException(Exception e, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        request.setAttribute("exception", e);
        response.sendRedirect(ORSView.ERROR_CTL);
    }
    
    public static void handleExceptionDB(String page, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        setErrorMessage("DATABASE SERVER DOWN....!!", request);
        forward(page, request, response);
    }
    
    public static void handleExceptionDBList(String page, BaseBean bean, int pageNo, int pageSize, HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException {
        setErrorMessage("DATABASE SERVER DOWN UNABLE TO LOAD LIST....!!", request);
        setList(new ArrayList<>(), request);
        setBean(bean, request);
        request.setAttribute("pageNo", pageNo);
		request.setAttribute("pageSize", pageSize);
		request.setAttribute("nextListSize", 0);
        forward(page, request, response);
    }
}
