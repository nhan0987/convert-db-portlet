package org.opencps.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.StringPool;

public class Main {
	
	private static final String USER_AGENT = "Mozilla/5.0";
	public static final String DOMAIN_NAME = "http://captinh.opencps.vn/api/jsonws/opencps-portlet.api/adddossier";
	public static final String URL_FRIENDLY = "api/jsonws/dlapp/get-file-entry/file-entry-id/";
	public static final String URL_FRIENDLY_GETFILE = "/c/document_library/get_file?";
	
	public static final String userName = "quantri.bgt@gmail.com";
	public static final String password = "quantr1.dvc@2016";
	public static final String targetDomain = "http://dichvucong.dangkiem.mt.gov.vn/";
	public static final long fileId = 245313;
	
	 public static void main(String []args) throws IOException, JSONException
			 {

			String fileURL = StringPool.BLANK;
			String urlString = targetDomain + URL_FRIENDLY + Long.toString(fileId);

			String userPass = userName +":"+ password;
			String basicAuth = "Basic " + new String(new Base64().encode(userPass.getBytes()));

			URL url = new URL(urlString);
			HttpURLConnection con = (HttpURLConnection) url.openConnection();

			con.setRequestMethod("GET");// By default it is GET request
			con.setRequestProperty("User-Agent", USER_AGENT);// add request header
			// String userpass = "admin.qa@fds.vn" + ":" + "LoveOpencps!2016@@";
			con.setRequestProperty("Authorization", basicAuth);
			con.setRequestProperty("Authorization", basicAuth);

			int responseCode = con.getResponseCode();
			System.out.println("Sending get request : " + url);
			System.out.println("Response code : " + responseCode);

			// Reading response from input Stream
			BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

			String output;

			StringBuffer response = new StringBuffer();

			while ((output = in.readLine()) != null) {
				response.append(output);
			}

			in.close();

			// printing result from response
			System.out.println("===response.toString():" + response.toString());


			JSONObject fileJSON = JSONFactoryUtil.createJSONObject(response.toString());

			System.out.println("===fileJSON:" + fileJSON);


			long groupId = fileJSON.getLong("groupId");

			String uuid = fileJSON.getString("uuid");

			//fileURL = getURLDownloadFile(groupId, uuid);

			
		}
}