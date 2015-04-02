package com.zsgj.foodsecurity.bean;
/**
 * 消息互通记录
 *
 */
public class Message {
        private long Id; /// 主键
        private long SenderId;  /// 发送方（食药人员、学校、家长）编号
        private String Content; /// 内容
        private int Type;/// 发送类型/// 1、食药人员发送,2、家长发送,3、学校发送
        private String DateTime;/// 发送时间
        private boolean Read; /// 是否读取
        private long OriginalMessageId; /// 原消息Id
		public long getId() {
			return Id;
		}
		public void setId(long id) {
			Id = id;
		}
		public long getSenderId() {
			return SenderId;
		}
		public void setSenderId(long senderId) {
			SenderId = senderId;
		}
		public String getContent() {
			return Content;
		}
		public void setContent(String content) {
			Content = content;
		}
		public int getType() {
			return Type;
		}
		public void setType(int type) {
			Type = type;
		}
		public String getDateTime() {
			return DateTime;
		}
		public void setDateTime(String dateTime) {
			DateTime = dateTime;
		}
		public boolean isRead() {
			return Read;
		}
		public void setRead(boolean read) {
			Read = read;
		}
		public long getOriginalMessageId() {
			return OriginalMessageId;
		}
		public void setOriginalMessageId(long originalMessageId) {
			OriginalMessageId = originalMessageId;
		}
        
}
