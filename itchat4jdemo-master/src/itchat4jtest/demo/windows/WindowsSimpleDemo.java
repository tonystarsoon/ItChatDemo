package itchat4jtest.demo.windows;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.alibaba.fastjson.JSONObject;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.tools.DownloadTools;

public class WindowsSimpleDemo implements IMsgHandlerFace {

	@Override
	public String textMsgHandle(JSONObject msg) {
		String text = msg.getString("Text");
		String result = "�յ��ı���Ϣ�� " + text;
		return result;
	}

	@Override
	public String picMsgHandle(JSONObject msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + ".jpg"; // ����ʹ���յ�ͼƬ��ʱ����Ϊ�ļ���
		String picPath = "D://itchat4j/pic" + File.separator + fileName; // ����ͼƬ��·��
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.PIC.getType(), picPath); // ���ô˷���������ͼƬ
		return "ͼƬ����ɹ�";
	}

	@Override
	public String voiceMsgHandle(JSONObject msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + ".mp3"; // ����ʹ���յ�������ʱ����Ϊ�ļ���
		String voicePath = "D://itchat4j/voice" + File.separator + fileName; // ����������·��
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VOICE.getType(), voicePath); // ���ô˷�������������
		return "��������ɹ�";
	}

	@Override
	public String viedoMsgHandle(JSONObject msg) {
		String fileName = new SimpleDateFormat("yyyy-MM-dd-HH-mm-ss").format(new Date()) + ".mp4"; // ����ʹ���յ�С��Ƶ��ʱ����Ϊ�ļ���
		String viedoPath = "D://itchat4j/viedo" + File.separator + fileName;// ����С��Ƶ��·��
		DownloadTools.getDownloadFn(msg, MsgTypeEnum.VIEDO.getType(), viedoPath);// ���ô˷���������С��Ƶ
		return "��Ƶ����ɹ�";
	}

	public static void main(String[] args) {
		IMsgHandlerFace msgHandler = new WindowsSimpleDemo();
		String qrPath = "D://itchat4j/login";
		// Wechat wechat = new Wechat(msgHandler,
		// "/home/itchat4j/demo/itchat4j/login");
		Wechat wechat = new Wechat(msgHandler, qrPath);
		wechat.start();
	}

	@Override
	public String nameCardMsgHandle(JSONObject arg0) {
		return "�յ���Ƭ��Ϣ";
	}
}
