package itchat4jtest.demo.demo1;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;

/**
 * 
 * @author https://github.com/yaphone
 * @date ����ʱ�䣺2017��4��28�� ����12:44:10
 * @version 1.0
 *
 */
public class Mytest {
	public static void main(String[] args) {
		String qrPath = "D://itchat4j//login"; // �����½��ά��ͼƬ��·��
		IMsgHandlerFace msgHandler = new SimpleDemo(); // ʵ��IMsgHandlerFace�ӿڵ���
		Wechat wechat = new Wechat(msgHandler, qrPath); // ��ע�롿
		wechat.start(); // �������񣬻���qrPath������һ�Ŷ�ά��ͼƬ��ɨ�輴�ɵ�½��ע�⣬��ά��ͼƬ�������һ��ʱ��δɨ�����ڣ�����ʱ���Զ����£������������Ҫ���´�ͼƬ
	}

}
