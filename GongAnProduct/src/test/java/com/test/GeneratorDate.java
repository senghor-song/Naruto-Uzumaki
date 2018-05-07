package com.test;

/**
 * 生成随机数据到数据库中
 * @author Senghor<br>
 * @date 2018年5月2日 下午1:51:26
 */
public class GeneratorDate {
    public static void main(String[] args) {
        TABLE_Control_Person_Extend_Generator_1 table_Control_Person_Extend = new TABLE_Control_Person_Extend_Generator_1(1000);
        TABLE_DYNAMIC_INFO_Generator_3 table_DYNAMIC_INFO = new TABLE_DYNAMIC_INFO_Generator_3(1000);
        TABLE_Leave_Person_Generator_4 table_Leave_Person = new TABLE_Leave_Person_Generator_4(1000);
        TABLE_Control_Monitor_Generator_5 table_Control_Monitor = new TABLE_Control_Monitor_Generator_5(1000);
        TABLE_Control_Monitor_Alarm_Generator_6 table_Control_Monitor_Alarm = new TABLE_Control_Monitor_Alarm_Generator_6(1000);
    }
}
