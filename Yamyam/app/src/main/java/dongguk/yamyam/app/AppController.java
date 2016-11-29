package dongguk.yamyam.app;

import android.support.multidex.MultiDexApplication;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Created by DK on 2016-10-13.
 */
public class AppController extends MultiDexApplication {

	public static final String TAG = AppController.class.getSimpleName();

	private RequestQueue mRequestQueue;

	private static AppController mInstance;

	public String key;
	public String name;
	public String address;
	public String subject;
	public String phone;
	public String type;
	public String date;
	public String latX;
	public String longY;
	public float distance;
	public float score;
	public boolean favorite;

	public String getKey() { return key; }
	public void setKey(String key) { this.key = key; }

	public String getSname() { return name; }
	public void setSname(String name) { this.name = name; }

	public String getAddr() { return address; }
	public void setAddr(String address) { this.address = address; }

	public String getSubject() { return subject; }
	public void setSubject(String subject) { this.subject = subject; }

	public String getPhone() { return phone; }
	public void setPhone(String phone) { this.name = phone; }

	public String getDate() { return date; }
	public void setDate(String date) { this.date = date; }

	public String getType() { return type; }
	public void setType(String type) { this.date = type; }

	public String getLatX() { return latX; }
	public void setLatX(String latX) { this.latX = latX; }

	public String getLongY() { return longY; }
	public void setLongY(String longY) { this.longY = longY; }

	public float getDistance() { return distance; }
	public void setDistance(float distance) { this.distance = distance; }

	public float getScore() { return score; }
	public void setScore(float score) { this.distance = score; }

	public boolean getFavorite() { return favorite; }
	public void setFavorite(boolean favorite) { this.favorite = favorite; }

	@Override
	public void onCreate() {
		super.onCreate();
		mInstance = this;
	}

	public static synchronized AppController getInstance() {
		return mInstance;
	}

	public RequestQueue getRequestQueue() {
		if (mRequestQueue == null) {
			mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		}

		return mRequestQueue;
	}

	public <T> void addToRequestQueue(Request<T> req, String tag) {
		req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
		getRequestQueue().add(req);
	}

	public <T> void addToRequestQueue(Request<T> req) {
		req.setTag(TAG);
		getRequestQueue().add(req);
	}

	public void cancelPendingRequests(Object tag) {
		if (mRequestQueue != null) {
			mRequestQueue.cancelAll(tag);
		}
	}
}