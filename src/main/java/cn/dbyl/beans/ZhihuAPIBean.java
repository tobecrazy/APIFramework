package main.java.cn.dbyl.beans;

import java.util.List;
/**
 * 
 * @author young
 *
 */
public class ZhihuAPIBean {
	private List<Creatives> creatives;

	public void setCreatives(List<Creatives> creatives) {
		this.creatives = creatives;
	}

	public List<Creatives> getCreatives() {
		return creatives;
	}

	public class Creatives {

		private String url;

		private int startTime;

		private List<String> impressionTracks;
		private int type;
		private String id;

		public void setUrl(String url) {
			this.url = url;
		}

		public String getUrl() {
			return url;
		}

		public void setStartTime(int startTime) {
			this.startTime = startTime;
		}

		public int getStartTime() {
			return startTime;
		}

		public void setImpressionTracks(List<String> impressionTracks) {
			this.impressionTracks = impressionTracks;
		}

		public List<String> getImpressionTracks() {
			return impressionTracks;
		}

		public void setType(int type) {
			this.type = type;
		}

		public int getType() {
			return type;
		}

		public void setId(String id) {
			this.id = id;
		}

		public String getId() {
			return id;
		}

	}
}
