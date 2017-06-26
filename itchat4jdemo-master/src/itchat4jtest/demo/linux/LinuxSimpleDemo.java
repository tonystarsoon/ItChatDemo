package itchat4jtest.demo.linux;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import cn.zhouyafeng.itchat4j.Wechat;
import cn.zhouyafeng.itchat4j.face.IMsgHandlerFace;
import cn.zhouyafeng.itchat4j.utils.MyHttpClient;

public class LinuxSimpleDemo implements IMsgHandlerFace {

	MyHttpClient myHttpClient = MyHttpClient.getInstance();
	String apiKey = "597b34bea4ec4c85a775c469c84b6817"; // �������������ͼ�������API�ӿڣ�ÿ��ֻ��5000�ε��ã������Լ�ȥ����һ������ѵ�:)
	Logger logger = Logger.getLogger("TulingRobot");

	@Override
	public String textMsgHandle(JSONObject msg) {
		String result = "";
		String text = msg.getString("Text");
		String url = "http://www.tuling123.com/openapi/api";
		Map<String, String> paramMap = new HashMap<String, String>();
		paramMap.put("key", apiKey);
		paramMap.put("info", text);
		paramMap.put("userid", "123456");
		String paramStr = JSON.toJSONString(paramMap);
		try {
			HttpEntity entity = myHttpClient.doPost(url, paramStr);
			result = EntityUtils.toString(entity, "UTF-8");
			JSONObject obj = JSON.parseObject(result);
			if (obj.getString("code").equals("100000")) {
				result = obj.getString("text");
			} else {
				result = "��������";
			}
		} catch (Exception e) {
			logger.info(e.getMessage());
		}
		return result;
	}

	@Override
	public String picMsgHandle(JSONObject msg) {

		return "�յ�ͼƬ";
	}

	@Override
	public String voiceMsgHandle(JSONObject msg) {

		return "�յ�����";
	}

	@Override
	public String viedoMsgHandle(JSONObject msg) {

		return "�յ���Ƶ";
	}

	public static void main(String[] args) {
		IMsgHandlerFace msgHandler = new LinuxSimpleDemo();
		Wechat wechat = new Wechat(msgHandler, "/home/itchat4j/demo/itchat4j/login");
		wechat.start();
	}

	@Override
	public String nameCardMsgHandle(JSONObject arg0) {
		return "�յ���Ƭ��Ϣ";
	}
}
