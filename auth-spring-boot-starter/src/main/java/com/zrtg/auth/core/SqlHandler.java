package com.zrtg.auth.core;

import com.zrtg.auth.properties.AuthConfigurationProperties;
import org.springframework.util.StringUtils;

import javax.annotation.PostConstruct;

public class SqlHandler {

    private AuthConfigurationProperties authConfigurationProperties;

    private static final String PERMISSION_FIELDS_FOR_UPDATE = "parent_id, permission_name, "
            + "permission_code, permission_method, permission_path, permission_type, "
            + "remark, created, updated";

    private static String TABLE;

    private static String PERMISSION_FIELDS;

    public static String BASE_FIND_STATEMENT;
    public static String DEFAULT_SELECT_STATEMENT;
    public static String DEFAULT_INSERT_STATEMENT;
    public static String DEFAULT_UPDATE_STATEMENT;
    public static String DEFAULT_UPDATE_PERMISSION_NAME_STATEMENT;
    public static String DEFAULT_SELECT_ID_STATEMENT;

    public SqlHandler(AuthConfigurationProperties authConfigurationProperties) {
        this.authConfigurationProperties = authConfigurationProperties;
    }

    /**
     * 静态变量可通过PostConstruct赋值，可以去了解下优先级（类加载的先后顺序）
     */
    @PostConstruct
    public void init() {
        if (StringUtils.isEmpty(authConfigurationProperties.getConfig().getTable())) {
            throw new RuntimeException("can't find table");
        }
        TABLE = authConfigurationProperties.getConfig().getTable();
        PERMISSION_FIELDS = "id, " + PERMISSION_FIELDS_FOR_UPDATE;
        BASE_FIND_STATEMENT = "select id, " + PERMISSION_FIELDS + " from " + TABLE;
        DEFAULT_SELECT_STATEMENT = BASE_FIND_STATEMENT + " where id = ?";
        DEFAULT_INSERT_STATEMENT = "insert into " + TABLE + " ("
                + PERMISSION_FIELDS + ") values (?,?,?,?,?,?,?,?,?,?)";
        DEFAULT_UPDATE_STATEMENT = "update " + TABLE + " set "
                + PERMISSION_FIELDS_FOR_UPDATE.replaceAll(", ", "=?, ") + "=? where id = ?";
        DEFAULT_UPDATE_PERMISSION_NAME_STATEMENT = "update " + TABLE
                + " set permission_name = ? where id = ?";
        DEFAULT_SELECT_ID_STATEMENT = "select id from " + TABLE + " where id = ?";
    }

}
