
package org.opencps.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import com.liferay.portal.kernel.json.JSONException;
import com.liferay.portal.kernel.json.JSONFactoryUtil;
import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.Base64;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;

public class GetFileFromURL {

	private static Log _log = LogFactoryUtil.getLog(GetFileFromURL.class);

	private static final String USER_AGENT = "Mozilla/5.0";
	public static final String DOMAIN_NAME = "http://qa.opencps.vn";
	public static final String URL_FRIENDLY = "api/jsonws/dlapp/get-file-entry/file-entry-id/";
	public static final String URL_FRIENDLY_GETFILE = "/c/document_library/get_file?";

	public GetFileFromURL(long fileEntryId, String baseURL, String userName, String password) {

		try {
			this.fileId = fileEntryId;
			this.targetDomain = baseURL;
			this.userName = userName;
			this.password = password;
			getFileFromURL();

		}
		catch (Exception e) {

		}
	}

	private String getFileURL()
		throws IOException, JSONException {

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

		int responseCode = con.getResponseCode();
		_log.info("Sending get request : " + url);
		_log.info("Response code : " + responseCode);

		// Reading response from input Stream
		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

		String output;

		StringBuffer response = new StringBuffer();

		while ((output = in.readLine()) != null) {
			response.append(output);
		}

		in.close();

		// printing result from response

		JSONObject fileJSON = JSONFactoryUtil.createJSONObject(response.toString());

		_log.info("===fileJSON:" + fileJSON);

		this.jsonData = fileJSON;

		long groupId = fileJSON.getLong("groupId");

		String uuid = fileJSON.getString("uuid");

		fileURL = getURLDownloadFile(groupId, uuid);

		return fileURL;
	}

	/**
	 * @param baseURL
	 * @param groupId
	 * @param uuid
	 * @return
	 */
	private String getURLDownloadFile(long groupId, String uuid) {

		_log.info("=====groupId:" + groupId);
		_log.info("=====uuid:" + uuid);

		StringBuffer result = new StringBuffer();

		try {

			if (groupId > 0 | Validator.isNotNull(uuid)) {

				result.append(targetDomain);
				result.append(URL_FRIENDLY_GETFILE);
				result.append("uuid=").append(uuid);
				result.append(StringPool.AMPERSAND).append("groupId=").append(groupId);

			}
			

		}
		catch (Exception e) {

		}

		return result.toString();
	}

	public void getFileFromURL()
		throws IOException, JSONException {

		String fileURL = getFileURL();

		_log.info("===fileURL" + fileURL);

		HttpURLConnection connection = null;
		byte[] bytes = null;

		if (Validator.isNotNull(fileURL)) {

			try {
				URL url = new URL(fileURL);

				connection = (HttpURLConnection) url.openConnection();
				connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
				connection.addRequestProperty("User-Agent", "Mozilla");
				connection.addRequestProperty("Referer", "google.com");

				connection.setInstanceFollowRedirects(false);
				connection.setConnectTimeout(5000); // 5s
				connection.setReadTimeout(5000); // 5s

				int status = connection.getResponseCode();

				boolean redirect = false;

				// normally, 3xx is redirect
				if (status != HttpURLConnection.HTTP_OK) {
					if (status == HttpURLConnection.HTTP_MOVED_TEMP ||
						status == HttpURLConnection.HTTP_MOVED_PERM ||
						status == HttpURLConnection.HTTP_SEE_OTHER)
						redirect = true;
				}

				if (redirect) {

					// get redirect url from "location" header field
					String newUrl = connection.getHeaderField("Location");

					// get the cookie if need, for login
					String cookies = connection.getHeaderField("Set-Cookie");

					// open the new connnection again
					connection = (HttpURLConnection) new URL(newUrl).openConnection();

					connection.setRequestProperty("Cookie", cookies);
					connection.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
					connection.addRequestProperty("User-Agent", "Mozilla");
					connection.addRequestProperty("Referer", "google.com");

					connection.setConnectTimeout(5000); // 5s
					connection.setReadTimeout(5000); // 5s

					status = connection.getResponseCode();
				}

				if (status == HttpURLConnection.HTTP_OK) {
					InputStream is = connection.getInputStream();
					// File file = FileUtil.createTempFile(is);
					// long size = connection.getContentLengthLong();
					// _log.info("===fileURL===" + fileURL + "===" +
					// file.getAbsolutePath());
					// _log.info("===fileURL===" + fileURL + "===" + size);

					bytes = FileUtil.getBytes(is);

					this.fileBytes = bytes;

					// FileUtil.createTempFile(bytes);
				}
			}
			catch (IOException ioe) {
				throw new IOException(ioe.getMessage());
			}
			finally {
				connection.disconnect();
			}
		}

	}

	public byte[] fileBytes;
	public JSONObject jsonData;
	public String userName;
	public String password;
	public long fileId;
	public String targetDomain;

	public Long getFileId() {

		return fileId;
	}

	public void setFileId(Long fileId) {

		this.fileId = fileId;
	}

	public String getTargetDomain() {

		return targetDomain;
	}

	public void setTargetDomain(String targetDomain) {

		this.targetDomain = targetDomain;
	}

	public String getUserName() {

		return userName;
	}

	public void setUserName(String userName) {

		this.userName = userName;
	}

	public String getPassword() {

		return password;
	}

	public void setPassword(String password) {

		this.password = password;
	}

	public byte[] getFileBytes() {

		return fileBytes;
	}

	public void setFileBytes(byte[] fileBytes) {

		this.fileBytes = fileBytes;
	}

	public JSONObject getJsonData() {

		return jsonData;
	}

	public void setJsonData(JSONObject jsonData) {

		this.jsonData = jsonData;
	}

}
