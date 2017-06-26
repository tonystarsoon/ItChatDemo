package cn.zhouyafeng.itchat4j.utils.tools;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.HttpEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONObject;

import cn.zhouyafeng.itchat4j.core.Core;
import cn.zhouyafeng.itchat4j.utils.MyHttpClient;
import cn.zhouyafeng.itchat4j.utils.enums.MsgTypeEnum;
import cn.zhouyafeng.itchat4j.utils.enums.URLEnum;

/**
 * 下载工具类
 * 
 * @author https://github.com/yaphone
 * @date 创建时间：2017年4月21日 下午11:18:46
 * @version 1.0
 *
 */
public class DownloadTools {
	private static Logger logger = Logger.getLogger("DownloadTools");
	private static Core core = Core.getInstance();
	private static MyHttpClient myHttpClient = core.getMyHttpClient();

	/**
	 * 处理下载任务
	 * 
	 * @author https://github.com/yaphone
	 * @date 2017年4月21日 下午11:00:25
	 * @param url
	 * @param msgId
	 * @param path
	 * @return
	 */
	public static Object getDownloadFn(JSONObject msg, String type, String path) {
		Map<String, String> headerMap = new HashMap<String, String>();
		List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
		String url = "";
		if (type.equals(MsgTypeEnum.PIC.getType())) {
			url = String.format(URLEnum.WEB_WX_GET_MSG_IMG.getUrl(), core.getLoginInfo().get("url"));
		} else if (type.equals(MsgTypeEnum.VOICE.getType())) {
			url = String.format(URLEnum.WEB_WX_GET_VOICE.getUrl(), core.getLoginInfo().get("url"));
		} else if (type.equals(MsgTypeEnum.VIEDO.getType())) {
			headerMap.put("Range", "bytes=0-");
			url = String.format(URLEnum.WEB_WX_GET_VIEDO.getUrl(), core.getLoginInfo().get("url"));
		}
		params.add(new BasicNameValuePair("msgid", msg.getString("NewMsgId")));
		params.add(new BasicNameValuePair("skey", (String) core.getLoginInfo().get("skey")));
		HttpEntity entity = myHttpClient.doGet(url, params, true, headerMap);
		try {
			OutputStream out = new FileOutputStream(path);
			byte[] bytes = EntityUtils.toByteArray(entity);
			out.write(bytes);
			out.flush();
			out.close();
			// Tools.printQr(path);

		} catch (Exception e) {
			logger.info(e.getMessage());
			return false;
		}
		return null;
	};

}
