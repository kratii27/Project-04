package in.co.rays.proj4.controller;

/**
 * ORSView interface contains constants for JSP view paths and corresponding
 * controller URLs for the ORSProject-04 application.
 * <p>
 * This interface centralizes all view and controller references to avoid
 * hardcoding in the codebase, making maintenance and navigation easier.
 * </p>
 * 
 * @author krati
 * @version 1.0
 */
public interface ORSView {

    /** Application context path */
    public String APP_CONTEXT = "/ORSProject-04";

    /** Base folder for JSP pages */
    public String PAGE_FOLDER = "/jsp";

    // -----------------------------------------------------------
    // Welcome Module
    // -----------------------------------------------------------

    /** Welcome page JSP */
    public String WELCOME_VIEW = PAGE_FOLDER + "/Welcome.jsp";

    /** Welcome controller URL */
    public String WELCOME_CTL = APP_CONTEXT + "/WelcomeCtl";

    // -----------------------------------------------------------
    // User Module
    // -----------------------------------------------------------

    /** User Registration JSP */
    public String USER_REGISTRATION_VIEW = PAGE_FOLDER + "/UserRegistrationView.jsp";

    /** User Registration Controller URL */
    public String USER_REGISTRATION_CTL = APP_CONTEXT + "/UserRegistrationCtl";

    /** Forget Password JSP */
    public String FORGET_PASSWORD_VIEW = PAGE_FOLDER + "/ForgetPasswordView.jsp";

    /** Forget Password Controller URL */
    public String FORGET_PASSWORD_CTL = APP_CONTEXT + "/ForgetPasswordCtl";

    /** Login JSP */
    public String LOGIN_VIEW = PAGE_FOLDER + "/LoginView.jsp";

    /** Login Controller URL */
    public String LOGIN_CTL = APP_CONTEXT + "/LoginCtl";

    /** My Profile JSP */
    public String MY_PROFILE_VIEW = PAGE_FOLDER + "/MyProfileView.jsp"; 

    /** My Profile Controller URL */
    public String MY_PROFILE_CTL = APP_CONTEXT + "/ctl/MyProfileCtl";

    /** Change Password JSP */
    public String CHANGE_PASSWORD_VIEW = PAGE_FOLDER + "/ChangePasswordView.jsp";

    /** Change Password Controller URL */
    public String CHANGE_PASSWORD_CTL = APP_CONTEXT + "/ctl/ChangePasswordCtl";

    // -----------------------------------------------------------
    // Marksheet Module
    // -----------------------------------------------------------

    /** Get Marksheet JSP */
    public String GET_MARKSHEET_VIEW = PAGE_FOLDER + "/GetMarksheetView.jsp";

    /** Get Marksheet Controller URL */
    public String GET_MARKSHEET_CTL = APP_CONTEXT + "/ctl/GetMarksheetCtl";

    /** Marksheet Merit List JSP */
    public String MARKSHEET_MERIT_LIST_VIEW = PAGE_FOLDER + "/MarksheetMeritListView.jsp";

    /** Marksheet Merit List Controller URL */
    public String MARKSHEET_MERIT_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetMeritListCtl";

    /** Marksheet View JSP */
    public String MARKSHEET_VIEW = PAGE_FOLDER + "/MarksheetView.jsp";

    /** Marksheet Controller URL */
    public String MARKSHEET_CTL = APP_CONTEXT + "/ctl/MarksheetCtl";

    /** Marksheet List JSP */
    public String MARKSHEET_LIST_VIEW = PAGE_FOLDER + "/MarksheetListView.jsp";

    /** Marksheet List Controller URL */
    public String MARKSHEET_LIST_CTL = APP_CONTEXT + "/ctl/MarksheetListCtl";

    // -----------------------------------------------------------
    // User Management Module
    // -----------------------------------------------------------

    /** User View JSP */
    public String USER_VIEW = PAGE_FOLDER + "/UserView.jsp";

    /** User Controller URL */
    public String USER_CTL = APP_CONTEXT + "/ctl/UserCtl";

    /** User List JSP */
    public String USER_LIST_VIEW = PAGE_FOLDER + "/UserListView.jsp";

    /** User List Controller URL */
    public String USER_LIST_CTL = APP_CONTEXT + "/ctl/UserListCtl";

    // -----------------------------------------------------------
    // Role Module
    // -----------------------------------------------------------

    /** Role View JSP */
    public String ROLE_VIEW = PAGE_FOLDER + "/RoleView.jsp";

    /** Role Controller URL */
    public String ROLE_CTL = APP_CONTEXT + "/ctl/RoleCtl";

    /** Role List JSP */
    public String ROLE_LIST_VIEW = PAGE_FOLDER + "/RoleListView.jsp";

    /** Role List Controller URL */
    public String ROLE_LIST_CTL = APP_CONTEXT + "/ctl/RoleListCtl";

    // -----------------------------------------------------------
    // College Module
    // -----------------------------------------------------------

    /** College View JSP */
    public String COLLEGE_VIEW = PAGE_FOLDER + "/CollegeView.jsp";

    /** College Controller URL */
    public String COLLEGE_CTL = APP_CONTEXT + "/ctl/CollegeCtl";

    /** College List JSP */
    public String COLLEGE_LIST_VIEW = PAGE_FOLDER + "/CollegeListView.jsp";

    /** College List Controller URL */
    public String COLLEGE_LIST_CTL = APP_CONTEXT + "/ctl/CollegeListCtl";

    // -----------------------------------------------------------
    // Student Module
    // -----------------------------------------------------------

    /** Student View JSP */
    public String STUDENT_VIEW = PAGE_FOLDER + "/StudentView.jsp";

    /** Student Controller URL */
    public String STUDENT_CTL = APP_CONTEXT + "/ctl/StudentCtl";

    /** Student List JSP */
    public String STUDENT_LIST_VIEW = PAGE_FOLDER + "/StudentListView.jsp";

    /** Student List Controller URL */
    public String STUDENT_LIST_CTL = APP_CONTEXT + "/ctl/StudentListCtl";

    // -----------------------------------------------------------
    // Course Module
    // -----------------------------------------------------------

    /** Course View JSP */
    public String COURSE_VIEW = PAGE_FOLDER + "/CourseView.jsp";

    /** Course Controller URL */
    public String COURSE_CTL = APP_CONTEXT + "/ctl/CourseCtl";

    /** Course List JSP */
    public String COURSE_LIST_VIEW = PAGE_FOLDER + "/CourseListView.jsp";

    /** Course List Controller URL */
    public String COURSE_LIST_CTL = APP_CONTEXT + "/ctl/CourseListCtl";

    // -----------------------------------------------------------
    // Subject Module
    // -----------------------------------------------------------

    /** Subject View JSP */
    public String SUBJECT_VIEW = PAGE_FOLDER + "/SubjectView.jsp";

    /** Subject Controller URL */
    public String SUBJECT_CTL = APP_CONTEXT + "/ctl/SubjectCtl";

    /** Subject List JSP */
    public String SUBJECT_LIST_VIEW = PAGE_FOLDER + "/SubjectListView.jsp";

    /** Subject List Controller URL */
    public String SUBJECT_LIST_CTL = APP_CONTEXT + "/ctl/SubjectListCtl";

    // -----------------------------------------------------------
    // Timetable Module
    // -----------------------------------------------------------

    /** Timetable View JSP */
    public String TIMETABLE_VIEW = PAGE_FOLDER + "/TimetableView.jsp";

    /** Timetable Controller URL */
    public String TIMETABLE_CTL = APP_CONTEXT + "/ctl/TimetableCtl";

    /** Timetable List JSP */
    public String TIMETABLE_LIST_VIEW = PAGE_FOLDER + "/TimetableListView.jsp";

    /** Timetable List Controller URL */
    public String TIMETABLE_LIST_CTL = APP_CONTEXT + "/ctl/TimetableListCtl";

    // -----------------------------------------------------------
    // Faculty Module
    // -----------------------------------------------------------

    /** Faculty View JSP */
    public String FACULTY_VIEW = PAGE_FOLDER + "/FacultyView.jsp";

    /** Faculty Controller URL */
    public String FACULTY_CTL = APP_CONTEXT + "/ctl/FacultyCtl";

    /** Faculty List JSP */
    public String FACULTY_LIST_VIEW = PAGE_FOLDER + "/FacultyListView.jsp";

    /** Faculty List Controller URL */
    public String FACULTY_LIST_CTL = APP_CONTEXT + "/ctl/FacultyListCtl";
    


    // -----------------------------------------------------------
    // Error Page
    // -----------------------------------------------------------

    /** Error JSP */
    public String ERROR_VIEW = PAGE_FOLDER + "/ErrorView.jsp";

    /** Error Controller URL */
    public String ERROR_CTL = APP_CONTEXT + "/ctl/ErrorCtl";

    // -----------------------------------------------------------
    // Department Module
    // -----------------------------------------------------------

    /** Department View JSP */
    public String DEPARTMENT_VIEW = PAGE_FOLDER + "/DepartmentView.jsp";

    /** Department Controller URL */
    public String DEPARTMENT_CTL = APP_CONTEXT + "/ctl/DepartmentCtl";

    /** Department List JSP */
    public String DEPARTMENT_LIST_VIEW = PAGE_FOLDER + "/DepartmentListView.jsp";

    /** Department List Controller URL */
    public String DEPARTMENT_LIST_CTL = APP_CONTEXT + "/ctl/DepartmentListCtl";

   
    public String JAVA_DOC = APP_CONTEXT + "/doc/index.html";
    
    public String IMG_LOGO = "img/customLogo.jpg";
    
    /** Usecases */
    public String BATCH_VIEW = PAGE_FOLDER + "/BatchView.jsp";
    public String BATCH_CTL = APP_CONTEXT + "/ctl/BatchCtl";
    public String BATCH_LIST_VIEW = PAGE_FOLDER + "/BatchListView.jsp";
    public String BATCH_LIST_CTL = APP_CONTEXT + "/ctl/BatchListCtl";
    
    public String NOTIFICATION_VIEW = PAGE_FOLDER + "/NotificationView.jsp";
    public String NOTIFICATION_CTL = APP_CONTEXT + "/ctl/NotificationCtl";
    public String NOTIFICATION_LIST_VIEW = PAGE_FOLDER + "/NotificationListView.jsp";
    public String NOTIFICATION_LIST_CTL = APP_CONTEXT + "/ctl/NotificationListCtl";
    
    public String HOSTEL_VIEW = PAGE_FOLDER + "/HostelView.jsp";
    public String HOSTEL_CTL = APP_CONTEXT + "/ctl/HostelCtl";
    public String HOSTEL_LIST_VIEW = PAGE_FOLDER + "/HostelListView.jsp";
    public String HOSTEL_LIST_CTL = APP_CONTEXT + "/ctl/HostelListCtl";
    
    public String PORTFOLIO_VIEW = PAGE_FOLDER + "/PortfolioView.jsp";
    public String PORTFOLIO_CTL = APP_CONTEXT + "/ctl/PortfolioCtl";
    public String PORTFOLIO_LIST_VIEW = PAGE_FOLDER + "/PortfolioListView.jsp";
    public String PORTFOLIO_LIST_CTL = APP_CONTEXT + "/ctl/PortfolioListCtl";
    
    public String TICKET_CATEGORY_VIEW = PAGE_FOLDER + "/TicketCategoryView.jsp";
    public String TICKET_CATEGORY_CTL = APP_CONTEXT + "/ctl/TicketCategoryCtl";
    public String TICKET_CATEGORY_LIST_VIEW = PAGE_FOLDER + "/TicketCategoryListView.jsp";
    public String TICKET_CATEGORY_LIST_CTL = APP_CONTEXT + "/ctl/TicketCategoryListCtl";
   
    public String MUSIC_PLAYLIST_VIEW = PAGE_FOLDER + "/MusicPlaylistView.jsp";
    public String MUSIC_PLAYLIST_CTL = APP_CONTEXT + "/ctl/MusicPlaylistCtl";
    public String MUSIC_PLAYLIST_LIST_VIEW = PAGE_FOLDER + "/MusicPlaylistListView.jsp";
    public String MUSIC_PLAYLIST_LIST_CTL = APP_CONTEXT + "/ctl/MusicPlaylistListCtl";
    
    public String HOSTEL_ROOM_ALLOCATION_VIEW = PAGE_FOLDER + "/HostelRoomAllocationView.jsp";
    public String HOSTEL_ROOM_ALLOCATION_CTL = APP_CONTEXT + "/ctl/HostelRoomAllocationCtl";
    public String HOSTEL_ROOM_ALLOCATION_LIST_VIEW = PAGE_FOLDER + "/HostelRoomAllocationListView.jsp";
    public String HOSTEL_ROOM_ALLOCATION_LIST_CTL = APP_CONTEXT + "/ctl/HostelRoomAllocationListCtl";
}
