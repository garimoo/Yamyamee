package dongguk.yamyam.app;

/**
 * Created by DK on 2016-10-11.
 */
public class AppConfig {
	// Server user login url
	public static String ip = "nyamnyam.dothome.co.kr";

	public static String URL_LOGIN = "http://" + ip + "/yamyam_api/login.php";

	// Server user register url
	public static String URL_REGISTER = "http://" + ip + "/yamyam_api/register.php";
	// Server data input url
	public static String URL_NAME_SEARCH = "http://" + ip + "/yamyam_api/name-search.php";

	public static String URL_SEL_SEARCH = "http://" + ip + "/yamyam_api/select-search.php";

	public static String URL_DETAIL_SEARCH = "http://" + ip + "/yamyam_api/detail-search.php";

	public static String URL_SANI_SEARCH = "http://" + ip + "/yamyam_api/sanitation-search.php";

	public static String URL_REVIEW_SEARCH = "http://" + ip + "/yamyam_api/review-search.php";

	public static String URL_REVIEW_WRITE = "http://" + ip + "/yamyam_api/PhotoUpload/upload.php";

	public static String URL_FAVORITE = "http://" + ip + "/yamyam_api/favorite.php";

	public static String URL_LOAD_FAVORITE = "http://" + ip + "/yamyam_api/load-favorite.php";

	public static String URL_GPS_SEARCH = "http://" + ip + "/yamyam_api/gps-search.php";

	public static final String URL_UPLOAD_IMAGE = "http://" + ip + "/yamyam_api/PhotoUpload/upload.php";
}