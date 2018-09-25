package com.ruiec.web.service.impl;

import java.util.List;

import org.hibernate.Query;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ruiec.framework.server.support.service.impl.BaseServiceImpl;
import com.ruiec.web.entity.Banner;
import com.ruiec.web.service.BannerService;

/**
 * 登录背景图片服务类
 * 
 * @author yuankai
 * @Date 2017-12-02
 * */
@Service("bannerServiceImpl")
public class BannerServiceImpl extends BaseServiceImpl<Banner, String> implements BannerService {
	/**
	 * 查询默认登录背景图片服务类
	 * 
	 * @param
	 * @return
	 * @exception
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:07:11
	 */
	@Override
	@Transactional
	public int isShow() {
		String sql = "update t_sys_banner set isshow = '0'";
		Query query = getSession().createSQLQuery(sql);
		return query.executeUpdate();
	}

	/**
	 * 查询默认登录背景图片服务类
	 * 
	 * @param
	 * @return
	 * @exception
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:07:25
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Banner> isShow1() {
		String sql = "select * from T_SYS_BANNER WHERE ISSHOW='1'";
		Query query = getSession().createSQLQuery(sql).addEntity(Banner.class);
		List<Banner> list = query.list();
		return list;
	}

	/**
	 * 删除不是默认登录背景图片服务类
	 * 
	 * @param
	 * @return
	 * @exception
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:07:38
	 */
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Banner> deleteIsShow() {
		String sql = "select * from T_SYS_BANNER WHERE ISSHOW='1'";
		Query query = getSession().createSQLQuery(sql).addEntity(Banner.class);
		List<Banner> list = query.list();
		return list;
	}

	/**
	 * 修改默认登录背景图片服务类
	 * 
	 * @param
	 * @return
	 * @exception
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:07:49
	 */
	@Override
	@Transactional
	public void updateIsShow(Banner banner) {
		if(banner.getIsshow()==1) {
			int a = isShow();
			if(a>0){
				update(banner);
			}
	   }else {
		   update(banner);
	   }
	}
	/**
	 * 新增默认登录背景图片服务类
	 * 
	 * @param
	 * @return
	 * @exception
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:08:00
	 */
	@Override
	@Transactional
	public void saveBanner(Banner banner) {
		if (banner.getIsshow() == 1) {
			List<Banner> banners = isShow1();
			for (Banner banner2 : banners) {
				banner2.setIsshow(0);
				super.save(banner2);
			}
		}
		save(banner);
	}

	/**
	 * 默认登录背景图片服务类
	 * 
	 * @param
	 * @return
	 * @exception
	 * @author yuankai<br>
	 * @date 2018年1月26日 下午3:08:11
	 */
	@Override
	@Transactional
	public List<Banner> defaultPic() {
		List<Banner> banners = isShow1();
		for (Banner banner2 : banners) {
			banner2.setIsshow(1);
		}
		return banners;
	}
}
