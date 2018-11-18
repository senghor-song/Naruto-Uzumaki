package com.xiaoyi.ssm.util;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.quartz.TriggerUtils;
import org.quartz.impl.triggers.CronTriggerImpl;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.ListObjectsRequest;
import com.aliyun.oss.model.OSSObjectSummary;
import com.aliyun.oss.model.ObjectListing;

/**
 * @Description: 综合工具类
 * @author song
 * @date 2018年6月29日 上午10:12:21
 */
public class Utils {

	@SuppressWarnings("unused")
	private static Logger logger = Logger.getLogger(Utils.class);
	
	/**
	 * @Description: 获取UUID
	 * @author song
	 * @date 2018年6月29日 上午10:12:44
	 */
	public static String getUUID() {
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		return uuid;
	}

	/**
	 * @Description: 用逗号拼接字符串
	 * @author song
	 * @date 2018年7月2日 下午1:52:43
	 */
	public static String getString(String[] strings) {
		String string = "";
		for (int i = 0; i < strings.length; i++) {
			string += strings[i];
			if (strings.length != i + 1) {
				string += ",";
			}
		}
		return string;
	}

	/**
	 * @Description: 上传多个图片到阿里云的服务器保存
	 * @author song
	 * @date 2018年7月9日 上午9:37:24  map.key = url ,size
	 */
	public static List<Map<String, Object>> getImageUrls(List<MultipartFile> list) {
		List<Map<String, Object>> filenameList = new ArrayList<Map<String, Object>>();
		if (list == null) {
			return filenameList;
		}
		try {
			// 将图片进行存储
			for (int i = 0; i < list.size(); i++) {
				Map<String, Object> map = new HashMap<String, Object>();
				MultipartFile mFile = list.get(i);

				CommonsMultipartFile cf = (CommonsMultipartFile) mFile;
				DiskFileItem fileItem = (DiskFileItem) cf.getFileItem();
				if (fileItem.getSize() == 0) {
					// 没有文件进行下一个文件
					continue;
				}
				InputStream fileContent = fileItem.getInputStream();
				String fileName = fileItem.getName();
				String UUID = Utils.getUUID();
				Integer size = (int) fileItem.getSize();
				map.put("size", size);
				map.put("name", UUID);
				fileName = UUID + fileName.substring(fileName.lastIndexOf("."));
				// 域名根节点
				String endpoint = Global.aliyunOssEndpoint;
				// 账户ID
				String accessKeyId = Global.aliyunOssAccessKeyId;
				// 账户密码
				String accessKeySecret = Global.aliyunOssAccessKeySecret;
				// 创建OSSClient实例。
				OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
				// 设置上传文件保存目录，按照年月日分文件夹存放
				Calendar now = Calendar.getInstance();
				String remoteFilePath = now.get(Calendar.YEAR) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.DAY_OF_MONTH) + "/";

				String bucketName = Global.aliyunOssBucketName;

				remoteFilePath += fileName;
				// 上传文件流。
				ossClient.putObject(bucketName, remoteFilePath, fileContent);
				// 关闭OSSClient。
				ossClient.shutdown();
				String imageUrl = Global.aliyunOssIpAddress + remoteFilePath;
				map.put("url", imageUrl);
				filenameList.add(map);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return filenameList;
	}

	/**
	 * @Description: 上传多个图片到阿里云的服务器保存
	 * @author song
	 * @date 2018年7月9日 上午9:37:24
	 */
	public static String getImageUrl(MultipartFile file) {
		try {
			// 将图片进行存储
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fileItem = (DiskFileItem) cf.getFileItem();
			if (fileItem.getSize() == 0) {
				// 没有文件进行下一个文件
				return null;
			}
			InputStream fileContent = fileItem.getInputStream();
			String fileName = fileItem.getName();
			fileName = Utils.getUUID() + fileName.substring(fileName.lastIndexOf("."));
			// 域名根节点
			String endpoint = Global.aliyunOssEndpoint;
			// 账户ID
			String accessKeyId = Global.aliyunOssAccessKeyId;
			// 账户密码
			String accessKeySecret = Global.aliyunOssAccessKeySecret;
			// 创建OSSClient实例。
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			// 设置上传文件保存目录，按照年月日分文件夹存放
			Calendar now = Calendar.getInstance();
			String remoteFilePath = now.get(Calendar.YEAR) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.DAY_OF_MONTH) + "/";

			String bucketName = Global.aliyunOssBucketName;

			remoteFilePath += fileName;
			// 上传文件流。
			ossClient.putObject(bucketName, remoteFilePath, fileContent);
			// 关闭OSSClient。
			ossClient.shutdown();
			String imageUrl = Global.aliyunOssIpAddress + remoteFilePath;
			return imageUrl;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * @Description: 上传单个图片到阿里云的服务器保存(VUE用)
	 * @author song
	 * @date 2018年7月9日 上午9:37:24
	 */
	public static String getImageUrl2(MultipartFile file) {
		try {
			// 将图片进行存储
			CommonsMultipartFile cf = (CommonsMultipartFile) file;
			DiskFileItem fileItem = (DiskFileItem) cf.getFileItem();
			if (fileItem.getSize() == 0) {
				// 没有文件进行下一个文件
				return null;
			}
			InputStream fileContent = fileItem.getInputStream();
			String fileName = fileItem.getContentType();
			fileName = fileName.replace("/", ".");
			fileName = Utils.getUUID() + fileName.substring(fileName.lastIndexOf("."));
			// 域名根节点
			String endpoint = Global.aliyunOssEndpoint;
			// 账户ID
			String accessKeyId = Global.aliyunOssAccessKeyId;
			// 账户密码
			String accessKeySecret = Global.aliyunOssAccessKeySecret;
			// 创建OSSClient实例。
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			// 设置上传文件保存目录，按照年月日分文件夹存放
			Calendar now = Calendar.getInstance();
			String remoteFilePath = "ball/"+now.get(Calendar.YEAR) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.DAY_OF_MONTH) + "/";

			String bucketName = Global.aliyunOssBucketName;

			remoteFilePath += fileName;
			// 上传文件流。
			ossClient.putObject(bucketName, remoteFilePath, fileContent);
			// 关闭OSSClient。
			ossClient.shutdown();
			String imageUrl = Global.aliyunOssIpAddress + remoteFilePath;
			return imageUrl;
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	/**  
	 * @Description: 根据http请求保存至OSS服务器
	 * @author 宋高俊  
	 * @param urlStr
	 * @return 
	 * @date 2018年11月7日 下午8:13:56 
	 */ 
	public static String getImageHttpUrl(String urlStr) {  
		HttpURLConnection httpUrl = null;  
		URL url = null;  
		try {
			url = new URL(urlStr);  
			httpUrl = (HttpURLConnection) url.openConnection();  
			httpUrl.connect();  
			
			InputStream fileContent = httpUrl.getInputStream();
			String fileName = httpUrl.getContentType();
			fileName = fileName.replace("/", ".");
			fileName = Utils.getUUID() + fileName.substring(fileName.lastIndexOf("."));
			// 域名根节点
			String endpoint = Global.aliyunOssEndpoint;
			// 账户ID
			String accessKeyId = Global.aliyunOssAccessKeyId;
			// 账户密码
			String accessKeySecret = Global.aliyunOssAccessKeySecret;
			// 创建OSSClient实例。
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			// 设置上传文件保存目录，按照年月日分文件夹存放
			Calendar now = Calendar.getInstance();
			String remoteFilePath = "ball/"+now.get(Calendar.YEAR) + "/" + (now.get(Calendar.MONTH) + 1) + "/" + now.get(Calendar.DAY_OF_MONTH) + "/";
	
			String bucketName = Global.aliyunOssBucketName;
	
			remoteFilePath += fileName;
			// 上传文件流。
			ossClient.putObject(bucketName, remoteFilePath, fileContent);
			// 关闭OSSClient。
			ossClient.shutdown();
			String imageUrl = Global.aliyunOssIpAddress + remoteFilePath;
			return imageUrl;
		} catch (Exception e) {
			return "";
		}
	} 
	
	/**  
	 * @Description: 获取4位数字的验证码
	 * @author song  
	 * @date 2018年7月9日 上午10:13:53 
	 */ 
	public static String getCode() {
		return "" + (int)((Math.random()*9+1)*1000);
	}
	
	/**  
	 * @Description: 获取复杂6位数的验证码
	 * @author song  
	 * @date 2018年7月9日 上午10:13:53 
	 */ 
	public static String getComplexCode() {
		String sources = "0123456789"; // 加上一些字母，就可以生成pc站的验证码了
		Random rand = new Random();
		StringBuffer code = new StringBuffer();
		for (int j = 0; j < 6; j++) 
		{
			code.append(sources.charAt(rand.nextInt(9)) + "");
		}
		return code.toString();
	}
	
	/**  
	 * @Description: 验证手机号是否正确
	 * @author song  
	 * @date 2018年7月9日 上午10:37:00 
	 */ 
	public static boolean getPhone(String phone) {
        String regex = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$";
        if(phone.length() != 11){
            return false;
        }else{
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(phone);
            boolean isMatch = m.matches();
            return isMatch;
        }
	}
	
	/**  
	 * @Description: Base64解码
	 * @author song  
	 * @param value:要解码的参数
	 * @date 2018年7月11日 下午7:34:38 
	 */ 
	public static String BaseDecode(final String value) {
		return new String(Base64.decodeBase64(value.getBytes()));
	}

	/**  
	 * @Description: Base64编码
	 * @author song  
	 * @param value:要编码的参数
	 * @date 2018年7月11日 下午7:35:02 
	 */ 
	public static String BaseEncode(final String value) {
		return new String(Base64.encodeBase64(value.getBytes()));
	}
	
	/**  
	 * @Description: MD5加密方法生成32位密文
	 * @author song  
	 * @date 2018年7月18日 上午11:11:42 
	 */ 
	public static String md5Password(String password) {

		try {
			// 得到一个信息摘要器
			MessageDigest digest = MessageDigest.getInstance("md5");
			byte[] result = digest.digest(password.getBytes());
			StringBuffer buffer = new StringBuffer();
			// 把每一个byte 做一个与运算 0xff;
			for (byte b : result) {
				// 与运算
				int number = b & 0xff;// 加盐
				String str = Integer.toHexString(number);
				if (str.length() == 1) {
					buffer.append("0");
				}
				buffer.append(str);
			}
			// 标准的md5加密后的结果
			return buffer.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return "";
		}
	}
	
    /**  
     * @Description: 房天下查询小区信息专用
     * @author song  
     * @date 2018年7月24日 下午5:00:37 
     */ 
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static List<Map> readStringXmlOut(String xml) {
    	List<Map> list = new ArrayList<>();
        Document doc = null;
        try {
            // 将字符串转为XML
            doc = DocumentHelper.parseText(xml); 
            // 获取根节点
            Element rootElt = doc.getRootElement(); 
            // 拿到根节点的名称
            
            //System.out.println("根节点：" + rootElt.getName()); 

            // 获取根节点下的子节点head
            Iterator iter = rootElt.elementIterator("Table1"); 
            // 遍历head节点
            while (iter.hasNext()) {
                Element itemEle = (Element) iter.next();
                // 拿到head下的子节点script下的字节点username的值
                String district = itemEle.elementTextTrim("district");
                String projname = itemEle.elementTextTrim("projname"); 
                String address = itemEle.elementTextTrim("address");
                String newcode = itemEle.elementTextTrim("newcode");
                String projtype = itemEle.elementTextTrim("projtype");
                String comerce = itemEle.elementTextTrim("comerce");

                Map map = new HashMap();
                map.put("district", district);
                map.put("projname", projname);
                map.put("address", address);
                map.put("newcode", newcode);
                map.put("projtype", projtype);
                map.put("comerce", comerce);
                list.add(map);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }
    
    /**  
     * @Description: 获取价格用分计算的数据
     * @author 宋高俊  
     * @return 
     * @date 2018年9月4日 下午3:29:58 
     */ 
    public static int doubleToInt(double price) {
    	return (new BigDecimal(Double.toString(price)).multiply(new BigDecimal("100"))).intValue();
	}
    
    /**  
     * @Description: 计算扣除手续费后剩余的金额
     * @author 宋高俊  
     * @param fee 手续费
     * @param amount 金额
     * @return 
     * @date 2018年9月19日 上午9:35:30 
     */ 
    public static double countFee(int feeSum, double amount) {
    	// 计算手续费率
    	float fee = (float) feeSum / 100;
		// 计算金额
		BigDecimal a = new BigDecimal(String.valueOf(fee));
		BigDecimal b = new BigDecimal(amount);
		Double feesum= a.multiply(b).doubleValue();
		
		return Arith.sub(amount, (double)Math.round(feesum*100)/100);
	}
    
    public static void main(String[] args) {
	    	// 域名根节点
			String endpoint = Global.aliyunOssEndpoint;
			// 账户ID
			String accessKeyId = Global.aliyunOssAccessKeyId;
			// 账户密码
			String accessKeySecret = Global.aliyunOssAccessKeySecret;
			String bucketName = Global.aliyunOssBucketName;
			// 创建OSSClient实例。
			OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
			final int maxKeys = 200;
			String nextMarker = null;
			ObjectListing objectListing;
			do {
			    objectListing = ossClient.listObjects(new ListObjectsRequest(bucketName).withMarker(nextMarker).withMaxKeys(maxKeys));
			    objectListing.setPrefix("ball/");
			    List<OSSObjectSummary> sums = objectListing.getObjectSummaries();
			    for (OSSObjectSummary s : sums) {
			        System.out.println("\t" + s.getKey());
			    }
			    nextMarker = objectListing.getNextMarker();
			} while (objectListing.isTruncated());
			// 关闭OSSClient。
			ossClient.shutdown();
	}
    
	/**  
	 * @Description: 运行cron表达式最近几次的运行时间
	 * @author 宋高俊  
	 * @param cron 表达式
	 * @param number 运行次数
	 * @date 2018年9月28日 下午5:53:49 
	 */ 
	public void cron(String cron, int number) {
		try {
			CronTriggerImpl cronTriggerImpl = new CronTriggerImpl();
			cronTriggerImpl.setCronExpression(cron); // 这个是重点，一行代码搞定
			List<Date> dates = TriggerUtils.computeFireTimes(cronTriggerImpl, null, number);
			for (Date date : dates) {
				System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date));
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 获取访问用户的客户端IP（适用于公网与局域网）.
	 */
	public static final String getIpAddr(final HttpServletRequest request){
		if (request == null) {
			return "";
		}
		String ipString = request.getHeader("x-forwarded-for");
		if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			ipString = request.getHeader("Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			ipString = request.getHeader("WL-Proxy-Client-IP");
		}
		if (StringUtils.isBlank(ipString) || "unknown".equalsIgnoreCase(ipString)) {
			ipString = request.getRemoteAddr();
		}
		// 多个路由时，取第一个非unknown的ip
		final String[] arr = ipString.split(",");
		for (final String str : arr) {
			if (!"unknown".equalsIgnoreCase(str)) {
				ipString = str;
				break;
			}
		}
		return ipString;
	}
}
