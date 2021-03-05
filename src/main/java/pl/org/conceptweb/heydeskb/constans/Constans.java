package pl.org.conceptweb.heydeskb.constans;

public class Constans {

// universal
public static String SUCCESS = "success";
public static String OK = "ok";
public static String ERROR = "error";
    
public static String IF_PASSWORD_IS_PROPER_SUCCESS_MESSAGE = "Sukces!";
public static String IF_PASSWORD_IDENTICAL_ERROR_MESSAGE = "Hasła się nie powtarzają! "; 
public static String IF_PASSWORD_NOT_TO_SHORT_OR_LONG_ERROR_MESSAGE = "Hasło ma niepoprawną długość";

// AuthController
public static String SIGN_UP_SUCCESS_MESSAGE = "sign up success";
public static String SIGN_UP_ERROR_MESSAGE = "username not available";

//AuthController
public static String CREATE_AUTHEBTICATION_TOKEN_SUCCESS_MESSAGE = "authentication token created";
public static String CREATE_AUTHEBTICATION_TOKEN_ERROR_MESSAGE = "";

public static String DELETE_USER_BY_HIMSELF_SUCCESS_MESSAGE = "your account is deleted";
public static String DELETE_USER_BY_HIMSELF_ERROR_MESSAGE = "you are not logged";
public static String ACCESS_ERROR_MESSAGE = "access denied";

//BuildingController
public static String ADD_BUILDING_SUCCESS_MESSAGE = "added a building";
public static String NAME_NOT_UNIQUE_ERROR_MESSAGE = "name not unique";

//CompanyController
public static String ADD_COMPANY_SUCCESS_MESSAGE = "company added";

//DeskController
public static String ADD_DESK_SUCCESS_MESSAGE = "desk added";
public static String CANCEL_DESK_RESERVATION_SUCCESS_MESSAGE = "reservation canceled";
public static String GET_AVAILABLE_DESK_IN_PERIOD_SUCCESS_MESSAGE = "list of available desks in given period";
public static String MAKE_RESERVATION_SUCCESS_MESSAGE = "desk reservation is made";
public static String GET_All_RESERVATIONS_BY_USER = "all reservations made by user";

//DeskReservationController
public static String GET_ALL_BY_COMPANY_SUCCESS_MESSAGE = "all reservations made by user signed to company";
public static String DELETE_DESK_RESERVATION_BY_ID_SUCCESS_MESSAGE = "reservation has been deleted";

//DropdownController
public static String GET_BUILGINGS_SUCCESS_MESSAGE = "";
public static String GET_FLOORS_SUCCESS_MESSAGE = "";
public static String GET_ROOMS_SUCCESS_MESSAGE = "";
public static String GET_DESKS_BY_ROOM_SUCCESS_MESSAGE = "";

//PortalStatsController
public static String BASIC_PORTAL_STATS_SUCCESS_MESSAGE = "";

//UserController
public static String ADD_USER_SUCCESS_MESSAGE = "";
public static String GET_ALL_USERS_BY_COMPANY_SUCCESS_MESSAGE = "";
public static String DELETE_USER_BY_ID_SUCCESS_MESSAGE = "user deleted";

public static String IF_USERNAME_IS_PROPRER_ERROR_MESSAGE = "user name not unique";
public static String IF_USERNAME_NOT_TO_SHORT_OR_LONG_ERROR_MESSAGE = "user name is to short or to long";
public static String IF_USERNAME_IS_PROPER_SUCCESS_MESSAGE = "user name is correct";

public static String ADD_FLOOR_SUCCESS_MESSAGE = "added a floor";
public static String ADD_ROOM_SUCCESS_MESSAGE = "added a room";

public static String GET_FLOOR_LIST_BY_COMPANY_SUCCESS_MESSAGE = "list of floors by company";
public static String DELETE_FLOOR_SUCCESS_MESSAGE = "floor deleted";


public static String GET_BUILDING_LIST_BY_COMPANY_SUCCESS_MESSAGE = "list of buildings by company";
public static String DELETE_BUILDING_SUCCESS_MESSAGE = "building deleted";

public static String GET_DESK_LIST_BY_COMPANY_SUCCESS_MESSAGE = "list of desk by company";
public static String DELETE_DESK_SUCCESS_MESSAGE = "desk deleted";

public static String HAS_AUTHORITY_ERROR_MESSAGE = "insufficient authority";
public static String AUTHORITY_ADMIN = "ADMIN";
        }
