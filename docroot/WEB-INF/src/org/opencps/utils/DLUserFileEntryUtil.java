package org.opencps.utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import org.opencps.expando.model.DomainConfigExt;

import com.liferay.portal.kernel.json.JSONObject;
import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.util.FileUtil;
import com.liferay.portal.kernel.util.MimeTypesUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.Validator;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portlet.assetpublisher.util.AssetPublisherUtil;
import com.liferay.portlet.documentlibrary.DuplicateFileException;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppServiceUtil;

public class DLUserFileEntryUtil {

	private String ROOT_FOLDER_NAME = "OPENCPS";
	private String PARENT_FOLDER_NAME = "templates";
	public static final String DOMAIN_NAME = "http://qa.opencps.vn/";

	private static Log _log = LogFactoryUtil.getLog(DLUserFileEntryUtil.class);

	public long getFileEntry(ActionRequest actionRequest,
			ActionResponse actionResponse, long fileId,long folderId) {

		long fileEntryId = 0;
		try {
			// 110072
			String targetDomain = StringPool.BLANK;
			String userName = StringPool.BLANK;
			String password = StringPool.BLANK;

			if (fileId > 0) {

				ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
						.getAttribute(WebKeys.THEME_DISPLAY);

				CommonUtils commonUtils = new CommonUtils();
				DomainConfigExt domainConfigExt = commonUtils
						.getDomainConfigData(themeDisplay.getCompanyId());

				targetDomain = domainConfigExt.getTargetDomain();
				userName = domainConfigExt.getUserName();
				password = domainConfigExt.getPassword();

				GetFileFromURL getFileFromURL = new GetFileFromURL(fileId,
						targetDomain, userName, password);

				byte[] file = null;
				JSONObject jsonObject = null;

				file = getFileFromURL.getFileBytes();
				_log.info("=====file:"+file);

				jsonObject = getFileFromURL.getJsonData();
				jsonObject.put("folderId", folderId);
				

				fileEntryId = uploadFile(actionRequest, actionResponse, file,
						jsonObject);
			}

			_log.info("=====fileEntryId:" + fileEntryId);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return fileEntryId;
	}
	
	public String getServiceInstructionUrl(ActionRequest actionRequest,ActionResponse actionResponse,String fileUrl){
		
		if(Validator.isNotNull(fileUrl)){
			
			long fileEntryId = 0;
			
			File file = null;
			
			try {
				file = GetFileFromURL.requestFileFromURL(fileUrl);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			if(Validator.isNotNull(file)){
				
				fileEntryId =uploadFile(actionRequest, actionResponse, file);
				
			}
		}
		
		return StringPool.BLANK;
	}
	
	private long uploadFile(ActionRequest actionRequest,
			ActionResponse actionResponse, File file) {

		long entryFileId = 0;
		FileEntry fileEntry = null;

		try {

			if (Validator.isNotNull(file)) {

				ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
						.getAttribute(WebKeys.THEME_DISPLAY);

				ServiceContext serviceContext = ServiceContextFactory
						.getInstance(actionRequest);

				serviceContext.setAddGroupPermissions(true);
				serviceContext.setAddGuestPermissions(true);

				long repositoryId = themeDisplay.getScopeGroupId();
				long folderId = 0;
				String sourceFileName = StringPool.BLANK;
				String extension = StringPool.BLANK;

				extension = "";

				Calendar cal = Calendar.getInstance();

				sourceFileName = StringPool.UNDERLINE + cal.getTimeInMillis();

				String description = StringPool.BLANK;
				String title = sourceFileName + StringPool.PERIOD + extension;
				String changeLog = StringPool.BLANK;

				String mimeType = MimeTypesUtil.getContentType(file);
				
				

				Folder folderFile = initFolderService(actionRequest,
						themeDisplay);

				if (Validator.isNotNull(folderFile)) {
					folderId = folderFile.getFolderId();
				}

				try {

					fileEntry = DLAppServiceUtil.addFileEntry(repositoryId,
							folderId, sourceFileName, mimeType, title,
							description, changeLog, file, serviceContext);
				} catch (Exception e) {

					if (e instanceof DuplicateFileException) {

					}
				}

				if (Validator.isNotNull(fileEntry)) {
					entryFileId = fileEntry.getFileEntryId();

					AssetPublisherUtil.addAndStoreSelection(actionRequest,
							DLFileEntry.class.getName(),
							fileEntry.getFileEntryId(), -1);

					AssetPublisherUtil.addRecentFolderId(actionRequest,
							DLFileEntry.class.getName(), folderId);
				}
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return entryFileId;
	}

	private long uploadFile(ActionRequest actionRequest,
			ActionResponse actionResponse, byte[] fileBytes,
			JSONObject jsonObject) {

		long entryFileId = 0;
		FileEntry fileEntry = null;

		try {

			if (Validator.isNotNull(fileBytes)) {

				ThemeDisplay themeDisplay = (ThemeDisplay) actionRequest
						.getAttribute(WebKeys.THEME_DISPLAY);

				ServiceContext serviceContext = ServiceContextFactory
						.getInstance(actionRequest);
				
				serviceContext.setAddGroupPermissions(true);
				serviceContext.setAddGuestPermissions(true);
				

				long repositoryId = themeDisplay.getScopeGroupId();
				long folderId = 0;
				String sourceFileName = StringPool.BLANK;
				String extension = StringPool.BLANK;

				//sourceFileName = jsonObject.getString("title");
				extension = jsonObject.getString("extension");
				
				//sourceFileName = sourceFileName.substring(0,sourceFileName.lastIndexOf(StringPool.PERIOD+extension));
				
				Calendar cal = Calendar.getInstance();
				
				sourceFileName = StringPool.UNDERLINE+cal.getTimeInMillis();

				String description = StringPool.BLANK;
				String title = sourceFileName+StringPool.PERIOD+extension;
				String changeLog = StringPool.BLANK;

				String mimeType = MimeTypesUtil.getContentType(title);
				
				folderId = jsonObject.getLong("folderId");
				
				if (folderId <= 0) {
					Folder folderFile = initFolderService(actionRequest,
							themeDisplay);

					if (Validator.isNotNull(folderFile)) {
						folderId = folderFile.getFolderId();
					}
				}
				
				try{

				fileEntry = DLAppServiceUtil.addFileEntry(repositoryId,
						folderId, sourceFileName, mimeType, title, description,
						changeLog, fileBytes, serviceContext);
				}catch(Exception e){
					
					if( e instanceof DuplicateFileException){
						
					}
				}
				
				
				if (Validator.isNotNull(fileEntry)) {
					entryFileId = fileEntry.getFileEntryId();

					AssetPublisherUtil.addAndStoreSelection(actionRequest,
							DLFileEntry.class.getName(),
							fileEntry.getFileEntryId(), -1);

					AssetPublisherUtil.addRecentFolderId(actionRequest,
							DLFileEntry.class.getName(), folderId);
				}
			}

		} catch (Exception e) {
			_log.error(e);
		}

		return entryFileId;
	}

	public Folder initFolderService(ActionRequest actionRequest,
			ThemeDisplay themeDisplay) {

		// Folder contains template service files

		String dateFolderName = DateTimeUtil.getStringDate();

		Folder folder = null;

		try {
			// Check ROOT folder exist

			long rootFolderId = 0;

			long parentFolderId = 0;

			boolean isRootFolderExist = isFolderExist(
					themeDisplay.getScopeGroupId(), 0, ROOT_FOLDER_NAME);

			ServiceContext serviceContext = ServiceContextFactory
					.getInstance(actionRequest);

			serviceContext.setAddGroupPermissions(true);
			serviceContext.setAddGuestPermissions(true);

			if (isRootFolderExist) {
				Folder rootFolder = null;

				rootFolder = DLAppServiceUtil.getFolder(
						themeDisplay.getScopeGroupId(), 0, ROOT_FOLDER_NAME);

				rootFolderId = rootFolder.getFolderId();

			} else {
				Folder rootFolder = DLAppServiceUtil.addFolder(
						themeDisplay.getScopeGroupId(), 0, ROOT_FOLDER_NAME,
						"All documents of OpenCPS", serviceContext);

				rootFolderId = rootFolder.getFolderId();
			}

			// Check parent folder exist

			boolean isParentFolderExist = isFolderExist(
					themeDisplay.getScopeGroupId(), rootFolderId,
					PARENT_FOLDER_NAME);

			if (isParentFolderExist) {
				Folder parentFolder = DLAppServiceUtil.getFolder(
						themeDisplay.getScopeGroupId(), rootFolderId,
						PARENT_FOLDER_NAME);

				parentFolderId = parentFolder.getFolderId();

			} else {
				Folder parentFolder = DLAppServiceUtil.addFolder(
						themeDisplay.getScopeGroupId(), rootFolderId,
						PARENT_FOLDER_NAME,
						"All documents of Service Template File",
						serviceContext);

				parentFolderId = parentFolder.getFolderId();
			}

			// Check DateFolder exist
			boolean isDateFolderExist = isFolderExist(
					themeDisplay.getScopeGroupId(), parentFolderId,
					dateFolderName);

			Folder dateFolder = null;

			if (isDateFolderExist) {

				dateFolder = DLAppServiceUtil.getFolder(
						themeDisplay.getScopeGroupId(), parentFolderId,
						dateFolderName);

			} else {
				dateFolder = DLAppServiceUtil
						.addFolder(
								themeDisplay.getScopeGroupId(),
								parentFolderId,
								dateFolderName,
								"All documents of Service Template File upload in a day",
								serviceContext);
			}

			folder = dateFolder;

		} catch (Exception e) {

		}

		return folder;
	}

	public boolean isFolderExist(long scopeGroupId, long parentFolderId,
			String folderName) {

		boolean folderExist = false;
		try {

			DLAppServiceUtil
					.getFolder(scopeGroupId, parentFolderId, folderName);
			folderExist = true;
		} catch (Exception e) {

		}

		return folderExist;
	}

}
