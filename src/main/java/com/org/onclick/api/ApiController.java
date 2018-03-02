package com.org.onclick.api;

public abstract class ApiController {

    public static final String APP_CONTEXT_PATH = "/mysbapp";
    private static final String API_PATH = "/api/v1";

    // Spring Boot Actuator services
    private static final String ACTUATOR_CONTEXT_PATH = "/actuator";
    public static final String AUTOCONFIG_ENDPOINT = ACTUATOR_CONTEXT_PATH + "/autoconfig";
    public static final String BEANS_ENDPOINT = ACTUATOR_CONTEXT_PATH + "/beans";
    public static final String CONFIGPROPS_ENDPOINT = ACTUATOR_CONTEXT_PATH + "/configprops";
    public static final String ENV_ENDPOINT = ACTUATOR_CONTEXT_PATH + "/env";
    public static final String MAPPINGS_ENDPOINT = ACTUATOR_CONTEXT_PATH + "/mappings";
    public static final String METRICS_ENDPOINT = ACTUATOR_CONTEXT_PATH + "/metrics";
    public static final String HEALTH_ENDPOINT = ACTUATOR_CONTEXT_PATH + "/health";
    public static final String SHUTDOWN_ENDPOINT = ACTUATOR_CONTEXT_PATH + "/shutdown";

    //
    public static final String HELLO_MESSAGE_ENDPOINT = API_PATH + "/hello";
    public static final String AUTHENTICATE_URL = API_PATH + "/authenticate";
    public static final String STUFF_URL = API_PATH + "/stuff";

    //
    public static final String ALL_PROPERTIES_ENDPOINT = API_PATH + "/properties";
    public static final String APP_PROPERTIES_ENDPOINT = API_PATH + "/properties/application";
    public static final String GLOBAL_PROPERTIES_ENDPOINT = API_PATH + "/properties/global";
    public static final String DATABASE_PROPERTIES_ENDPOINT = API_PATH + "/properties/database";
    public static final String EXTERNAL_PROPERTIES_ENDPOINT = API_PATH + "/properties/external";

    //
    public static final String ALL_USERS_ENDPOINT = API_PATH + "/allusers";
    public static final String ADD_USER_ENDPOINT = API_PATH + "/add-user";
    public static final String GET_USER_ENDPOINT = API_PATH + "/user/{id}";

}
