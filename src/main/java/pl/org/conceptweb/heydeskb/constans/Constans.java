package pl.org.conceptweb.heydeskb.constans;

public class Constans {

// universal
    public static String SUCCESS = "sukces"; //"success";
    public static String OK = "ok";
    public static String ERROR = "błąd"; //"error";
    public static String TRY_LATER = "spróbuj później";
    public static String INADEQUATE_DATA = "nieodpowiednie dane";
    public static String AUTHORITY_ADMIN = "ADMIN";
    public static String AUTHORITY_USER = "USER";
    public static String HAS_AUTHORITY_ERROR_MESSAGE = "brak uprawnień"; //"insufficient authority";

    public static String IF_PASSWORD_IS_PROPER_SUCCESS_MESSAGE = "hasło jest poprawne";
    public static String IF_PASSWORD_IDENTICAL_ERROR_MESSAGE = "hasła się nie powtarzają";
    public static String IF_PASSWORD_NOT_TO_SHORT_OR_LONG_ERROR_MESSAGE = "hasło ma niepoprawną długość";

// AuthController
    public static String SIGN_UP_SUCCESS_MESSAGE = "sign up success";
    public static String SIGN_UP_ERROR_MESSAGE = "username not available";

//AuthController
    public static String CREATE_AUTHEBTICATION_TOKEN_SUCCESS_MESSAGE = "wygenerowano token"; //"authentication token created";
    public static String CREATE_AUTHEBTICATION_TOKEN_ERROR_MESSAGE = "błąd generowania tokena";

    public static String DELETE_USER_BY_HIMSELF_SUCCESS_MESSAGE = "konto zostało skanowane"; //"your account is deleted";
    public static String DELETE_USER_BY_HIMSELF_ERROR_MESSAGE = "nie jesteś zalogowany"; //"you are not logged";
    public static String ACCESS_ERROR_MESSAGE = "odmowa dostępu"; //"access denied";

//BuildingController
    public static String ADD_BUILDING_SUCCESS_MESSAGE = "dodano budynek"; //"added a building";
    public static String NAME_NOT_UNIQUE_ERROR_MESSAGE = "obiekt o tej nazwie już istnieje"; //"name not unique";

//CompanyController
    public static String ADD_COMPANY_SUCCESS_MESSAGE = "dodano firmę"; //"company added";

//DeskController
    public static String ADD_DESK_SUCCESS_MESSAGE = "dodano biurko"; //"desk added";
    public static String GET_AVAILABLE_DESK_IN_PERIOD_SUCCESS_MESSAGE = "lista dostępnych biurek spełniających kryteria"; //"list of available desks in given period";
    public static String MAKE_RESERVATION_SUCCESS_MESSAGE = "dodano rezerwację"; //"desk reservation is made";
    public static String GET_All_RESERVATIONS_BY_USER = "wszystkie rezerwacje użytkownika"; //"all reservations made by user";

//DeskReservationController
    public static String GET_ALL_BY_COMPANY_SUCCESS_MESSAGE = "rezerwacje wszystkich pracowników formy"; //"all reservations made by user signed to company";
    public static String DELETE_DESK_RESERVATION_BY_ID_SUCCESS_MESSAGE = "rezerwacja skasowana"; //"reservation has been deleted";
    public static String CANCEL_DESK_RESERVATION_SUCCESS_MESSAGE = "rezerwacja skasowana"; //"reservation canceled";

//DropdownController
    public static String GET_BUILGINGS_SUCCESS_MESSAGE = "";
    public static String GET_FLOORS_SUCCESS_MESSAGE = "";
    public static String GET_ROOMS_SUCCESS_MESSAGE = "";
    public static String GET_DESKS_BY_ROOM_SUCCESS_MESSAGE = "";

//PortalStatsController
    public static String BASIC_PORTAL_STATS_SUCCESS_MESSAGE = "";

//UserController
    public static String ADD_USER_SUCCESS_MESSAGE = "dodano pracownika";
    public static String GET_ALL_USERS_BY_COMPANY_SUCCESS_MESSAGE = "lista wszystkich pracowników firmy";
    public static String DELETE_USER_BY_ID_SUCCESS_MESSAGE = "pracownik skasowany"; //"user deleted";

    public static String IF_USERNAME_IS_PROPRER_ERROR_MESSAGE = "nazwa pracownika jest już zajęta"; //"user name not unique";
    public static String IF_USERNAME_NOT_TO_SHORT_OR_LONG_ERROR_MESSAGE = "długość nazwy jest nieodpowiednia"; //"user name is to short or to long";
    public static String IF_USERNAME_IS_PROPER_SUCCESS_MESSAGE = "nazwa jest poprawna"; //"user name is correct";

    public static String ADD_FLOOR_SUCCESS_MESSAGE = "dodano piętro"; //"added a floor";
    public static String ADD_ROOM_SUCCESS_MESSAGE = "dodano pokój"; //"added a room";

    public static String GET_FLOOR_LIST_BY_COMPANY_SUCCESS_MESSAGE = "lista pięter w budynkach firmy"; //"list of floors by company";
    public static String DELETE_FLOOR_SUCCESS_MESSAGE = "piętro skasowane"; //"floor deleted";

    public static String GET_BUILDING_LIST_BY_COMPANY_SUCCESS_MESSAGE = "list budynków firmy"; //"list of buildings by company";
    public static String DELETE_BUILDING_SUCCESS_MESSAGE = "budynek skasowany"; //"building deleted";

    public static String GET_DESK_LIST_BY_COMPANY_SUCCESS_MESSAGE = "lista wszystkich biurek"; //"list of desk by company";
    public static String DELETE_DESK_SUCCESS_MESSAGE = "biurko skasowane"; //"desk deleted";

    public static String TIME_FORMAT_ERROR_MESSAGE = "błędny czas rezerwacji";
    public static String ADD_COMPANY = "dodano firmę i utworzono użytkownika";
    
    // input test 
    public static String IS_NOT_NULL_ERROR_MESSAGE = "nie wprowadzono danych ";
    public static String IS_LENGTH_APPROPRIATE_ERROR_MESSAGE = "długość wprowadzonego tekstu jest nieodpowiednia ";
    public static String IS_EEMAIL_ADDRESS_ERROR_MESSAGE = "nie przypomina adresu poczty elektronicznej ";
    public static String IS_NUMBER_ERROR_MESSAGE = "zawiera inne znaki niż cyfry ";
    
    // auth
    public static String CREDENCIALS_ERROR_MESSAGE = "niepoprawne dane logowania";
}
