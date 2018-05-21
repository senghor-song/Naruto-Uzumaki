package com.ruiec.springboot.controller.home;

import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 前台中央控制器
 * @author Senghor<br>
 * @date 2017年11月29日 下午1:48:11
 */
@Controller
@EnableAutoConfiguration
public class HomeController { 
	
	/**
	 * 前台首页
	 * @author Senghor<br>
	 * @date 2017年11月29日 下午1:48:35
	 */
	@RequestMapping(value = { "" , "/" , "/home" , "/index"})
	String home(HttpServletRequest request) {
	    return "home/index";
	}
	
	/*public List<User> getReplyListByRid(Long rid) {
		//根据评论id查询所有回复
		List<User> replyDOList = User.queryReplyByCid(rid);
		//没有回复则返回空
        if (replyDOList == null || replyDOList.size() == 0) {
            return new ArrayList<>();
        }

        List<User> replyDTOList = new ArrayList<>();
        List<User> parentList = new ArrayList<>();
        
        for (User replyDO : replyDOList) {
        	
        	ReplyDTO replyDTO = convertReplyToDTO(replyDO);
        	
            if (replyDTO.getReplyType() == ReplyType.COMMENT) {
                replyDTOList.add(replyDTO);
                parentList.add(replyDTO);
            } else {
                boolean foundParent = false;
                if (replyDTOList.size() > 0) {
                    for (ReplyDTO parent : parentList) {
                        if (parent.getId().equals(replyDTO.getReplyId())) {
                            if (parent.getNext() == null) {
                                parent.setNext(new ArrayList<User>());
                            }
                            parent.getNext().add(replyDTO);
                            parentList.add(replyDTO);
                            foundParent = true;
                            break;
                        }
                    }
                }
                if (!foundParent) {
                    throw new RuntimeException("sort reply error,should not go here.");
                }
            }
        }
        return replyDTOList;
  }*/
}
