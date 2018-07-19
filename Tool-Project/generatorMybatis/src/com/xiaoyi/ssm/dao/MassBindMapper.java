package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.MassBind;

public interface MassBindMapper {
    /**
     *
     * @mbggenerated 2018-06-25
     */
    int deleteByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2018-06-25
     */
    int insert(MassBind record);

    /**
     *
     * @mbggenerated 2018-06-25
     */
    int insertSelective(MassBind record);

    /**
     *
     * @mbggenerated 2018-06-25
     */
    MassBind selectByPrimaryKey(String id);

    /**
     *
     * @mbggenerated 2018-06-25
     */
    int updateByPrimaryKeySelective(MassBind record);

    /**
     *
     * @mbggenerated 2018-06-25
     */
    int updateByPrimaryKey(MassBind record);
}