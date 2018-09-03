package com.xiaoyi.ssm.dao;

import com.xiaoyi.ssm.model.Venue;

public interface VenueMapper extends BaseMapper<Venue, String>{


	Venue selectByManager(String managerid);
}