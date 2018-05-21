package com.ruiec.springboot.controller;

/**
 * 回复的枚举类
 * @author Senghor<br>
 * @date 2017年11月14日 下午4:54:03
 */
public enum ReplyType {
	COMMENT("commit_id"), 
	REPLY("reply_id");
	
	private String name;

	private ReplyType(String name){
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
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
