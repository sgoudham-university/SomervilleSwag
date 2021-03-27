package org.somerville.swag.data.domain;

public class Events {

    private static final String DATABASE_CONNECT_SUCCESS_SUFFIX = ".database_connect_success";
    private static final String DATABASE_CONNECT_FAILURE_SUFFIX = ".database_connect_failure";
    private static final String DATABASE_CREATE_TABLES_SUCCESS_SUFFIX = ".database_create_tables_success";
    private static final String DATABASE_CREATE_TABLES_FAILURE_SUFFIX = ".database_create_tables_failure";
    private static final String DATABASE_POPULATE_PRODUCT_TABLE_SUCCESS_SUFFIX = ".database_populate_product_table_success";
    private static final String DATABASE_POPULATE_PRODUCT_TABLE_FAILURE_SUFFIX = ".database_populate_product_table_failure";
    private static final String DATABASE_WRITE_SUCCESS_SUFFIX = ".database_write_success";
    private static final String DATABASE_WRITE_FAILURE_SUFFIX = ".database_write_failure";
    private static final String DATABASE_READ_SUCCESS_SUFFIX = ".database_read_success";
    private static final String DATABASE_READ_FAILURE_SUFFIX = ".database_read_failure";
    private static final String DATABASE_GET_CUSTOMER_SUCCESS_SUFFIX = ".database_get_customer_success";
    private static final String DATABASE_GET_CUSTOMER_FAILURE_SUFFIX = ".database_get_customer_failure";
    private static final String DATABASE_INSERT_CUSTOMER_SUCCESS_SUFFIX = ".database_insert_customer_success";
    private static final String DATABASE_INSERT_CUSTOMER_FAILURE_SUFFIX = ".database_insert_customer_failure";
    private static final String DATABASE_CUSTOMER_NOT_FOUND_SUFFIX = ".database_customer_not_found";
    private static final String DATABASE_CUSTOMER_MAP_SUCCESS_SUFFIX = ".database_customer_map_success";
    private static final String DATABASE_CUSTOMER_MAP_FAILURE_SUFFIX = ".database_customer_map_success";
    private static final String DATABASE_GET_ALL_PRODUCTS_IN_STOCK_SUCCESS_SUFFIX = ".database_get_all_products_in_stock_success";
    private static final String DATABASE_GET_ALL_PRODUCTS_IN_STOCK_FAILURE_SUFFIX = ".database_get_all_products_in_stock_failure";
    private static final String DATABASE_NO_PRODUCTS_IN_STOCK_SUFFIX = ".database_no_products_in_stock";
    private static final String DATABASE_ALL_PRODUCTS_MAP_SUCCESS_SUFFIX = ".database_all_products_map_success";
    private static final String DATABASE_ALL_PRODUCTS_MAP_FAILURE_SUFFIX = ".database_all_products_map_failure";
    private static final String DATABASE_CUSTOMER_ALREADY_EXISTS_SUFFIX = ".database_customer_already_exists";
    private static final String DATABASE_UPDATE_PRODUCT_STOCK_LEVEL_SUCCESS_SUFFIX = ".database_update_product_stock_level_success";
    private static final String DATABASE_UPDATE_PRODUCT_STOCK_LEVEL_FAILURE_SUFFIX = ".database_update_product_stock_level_failure";

    private static final String CUSTOMER_SIGNED_UP_SUFFIX = ".customer_signed_up";
    private static final String CUSTOMER_LOGGED_IN_SUFFIX = ".customer_logged_in";
    private static final String CUSTOMER_LOGGED_OUT_SUFFIX = ".customer_logged_out";
    private static final String CUSTOMER_ADD_PRODUCT_TO_BASKET_SUFFIX = ".customer_add_item_to_basket";
    private static final String CUSTOMER_REMOVE_PRODUCT_FROM_BASKET_SUFFIX = ".customer_remove_item_from_basket";
    private static final String CUSTOMER_CHECKOUT_SUFFIX = ".customer_checkout";



    public String getProjectPrefix() { return "org.somerville.swag"; }

    public String getDatabaseConnectSuccess() { return getProjectPrefix() + DATABASE_CONNECT_SUCCESS_SUFFIX; }
    public String getDatabaseConnectFailure() { return getProjectPrefix() + DATABASE_CONNECT_FAILURE_SUFFIX; }

    public String getDatabaseCreateTablesSuccess() { return getProjectPrefix() + DATABASE_CREATE_TABLES_SUCCESS_SUFFIX; }
    public String getDatabaseCreateTablesFailure() { return getProjectPrefix() + DATABASE_CREATE_TABLES_FAILURE_SUFFIX; }

    public String getDatabasePopulateProductTableSuccess() { return getProjectPrefix() + DATABASE_POPULATE_PRODUCT_TABLE_SUCCESS_SUFFIX; }
    public String getDatabasePopulateProductTableFailure() { return getProjectPrefix() + DATABASE_POPULATE_PRODUCT_TABLE_FAILURE_SUFFIX; }

    public String getDatabaseWriteSuccess() { return getProjectPrefix() + DATABASE_WRITE_SUCCESS_SUFFIX; }
    public String getDatabaseWriteFailure() { return getProjectPrefix() + DATABASE_WRITE_FAILURE_SUFFIX; }

    public String getDatabaseReadSuccess() { return getProjectPrefix() + DATABASE_READ_SUCCESS_SUFFIX; }
    public String getDatabaseReadFailure() { return getProjectPrefix() + DATABASE_READ_FAILURE_SUFFIX; }

    public String getDatabaseGetCustomerSuccess() { return getProjectPrefix() + DATABASE_GET_CUSTOMER_SUCCESS_SUFFIX; }
    public String getDatabaseGetCustomerFailure() { return getProjectPrefix() + DATABASE_GET_CUSTOMER_FAILURE_SUFFIX; }

    public String getDatabaseInsertCustomerSuccess() { return getProjectPrefix() + DATABASE_INSERT_CUSTOMER_SUCCESS_SUFFIX; }
    public String getDatabaseInsertCustomerFailure() { return getProjectPrefix() + DATABASE_INSERT_CUSTOMER_FAILURE_SUFFIX; }

    public String getDatabaseCustomerNotFound() { return getProjectPrefix() + DATABASE_CUSTOMER_NOT_FOUND_SUFFIX; }

    public String getDatabaseCustomerMapSuccess() { return getProjectPrefix() + DATABASE_CUSTOMER_MAP_SUCCESS_SUFFIX; }
    public String getDatabaseCustomerMapFailure() { return getProjectPrefix() + DATABASE_CUSTOMER_MAP_FAILURE_SUFFIX; }

    public String getDatabaseGetAllProductsInStockSuccess() { return getProjectPrefix() + DATABASE_GET_ALL_PRODUCTS_IN_STOCK_SUCCESS_SUFFIX; }
    public String getDatabaseGetAllProductsInStockFailure() { return getProjectPrefix() + DATABASE_GET_ALL_PRODUCTS_IN_STOCK_FAILURE_SUFFIX; }

    public String getDatabaseNoProductsInStock() { return getProjectPrefix() + DATABASE_NO_PRODUCTS_IN_STOCK_SUFFIX; }

    public String getDatabaseAllProductsMapSuccess() { return getProjectPrefix() + DATABASE_ALL_PRODUCTS_MAP_SUCCESS_SUFFIX; }
    public String getDatabaseAllProductsMapFailure() { return getProjectPrefix() + DATABASE_ALL_PRODUCTS_MAP_FAILURE_SUFFIX; }

    public String getDatabaseCustomerAlreadyExists() { return getProjectPrefix() + DATABASE_CUSTOMER_ALREADY_EXISTS_SUFFIX; }

    public String getDatabaseUpdateProductStockLevelSuccess() { return getProjectPrefix() + DATABASE_UPDATE_PRODUCT_STOCK_LEVEL_SUCCESS_SUFFIX; }
    public String getDatabaseUpdateProductStockLevelFailure() { return getProjectPrefix() + DATABASE_UPDATE_PRODUCT_STOCK_LEVEL_FAILURE_SUFFIX; }

    public String getCustomerSignedUp(){ return getProjectPrefix() + CUSTOMER_SIGNED_UP_SUFFIX; }

    public String getCustomerLoggedIn(){ return  getProjectPrefix() + CUSTOMER_LOGGED_IN_SUFFIX; }
    public String getCustomerLoggedOut() { return getProjectPrefix() + CUSTOMER_LOGGED_OUT_SUFFIX; }

    public String getCustomerAddProductToBasket() { return getProjectPrefix() + CUSTOMER_ADD_PRODUCT_TO_BASKET_SUFFIX; }
    public String getCustomerRemoveProductFromBasket() {return getProjectPrefix() + CUSTOMER_REMOVE_PRODUCT_FROM_BASKET_SUFFIX; }

    public String getCustomerCheckout() { return  getProjectPrefix() + CUSTOMER_CHECKOUT_SUFFIX; }
}
