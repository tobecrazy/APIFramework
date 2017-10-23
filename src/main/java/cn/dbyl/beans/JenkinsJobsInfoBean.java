package main.java.cn.dbyl.beans;

import java.util.List;

public class JenkinsJobsInfoBean {
	private String _class;
	private List<AssignedLabels> assignedLabels;
	private String mode;
	private String nodeDescription;
	private String nodeName;
	private int numExecutors;
	private String description;
	private List<Jobs> jobs;
	private OverallLoad overallLoad;
	private PrimaryView primaryView;
	private boolean quietingDown;
	private int slaveAgentPort;
	private UnlabeledLoad unlabeledLoad;
	private boolean useCrumbs;
	private boolean useSecurity;
	private List<Views> views;

	public String get_class() {
		return _class;
	}

	public void set_class(String _class) {
		this._class = _class;
	}

	public List<AssignedLabels> getAssignedLabels() {
		return assignedLabels;
	}

	public void setAssignedLabels(List<AssignedLabels> assignedLabels) {
		this.assignedLabels = assignedLabels;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getNodeDescription() {
		return nodeDescription;
	}

	public void setNodeDescription(String nodeDescription) {
		this.nodeDescription = nodeDescription;
	}

	public String getNodeName() {
		return nodeName;
	}

	public void setNodeName(String nodeName) {
		this.nodeName = nodeName;
	}

	public int getNumExecutors() {
		return numExecutors;
	}

	public void setNumExecutors(int numExecutors) {
		this.numExecutors = numExecutors;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Jobs> getJobs() {
		return jobs;
	}

	public void setJobs(List<Jobs> jobs) {
		this.jobs = jobs;
	}

	public OverallLoad getOverallLoad() {
		return overallLoad;
	}

	public void setOverallLoad(OverallLoad overallLoad) {
		this.overallLoad = overallLoad;
	}

	public PrimaryView getPrimaryView() {
		return primaryView;
	}

	public void setPrimaryView(PrimaryView primaryView) {
		this.primaryView = primaryView;
	}

	public boolean isQuietingDown() {
		return quietingDown;
	}

	public void setQuietingDown(boolean quietingDown) {
		this.quietingDown = quietingDown;
	}

	public int getSlaveAgentPort() {
		return slaveAgentPort;
	}

	public void setSlaveAgentPort(int slaveAgentPort) {
		this.slaveAgentPort = slaveAgentPort;
	}

	public UnlabeledLoad getUnlabeledLoad() {
		return unlabeledLoad;
	}

	public void setUnlabeledLoad(UnlabeledLoad unlabeledLoad) {
		this.unlabeledLoad = unlabeledLoad;
	}

	public boolean isUseCrumbs() {
		return useCrumbs;
	}

	public void setUseCrumbs(boolean useCrumbs) {
		this.useCrumbs = useCrumbs;
	}

	public boolean isUseSecurity() {
		return useSecurity;
	}

	public void setUseSecurity(boolean useSecurity) {
		this.useSecurity = useSecurity;
	}

	public List<Views> getViews() {
		return views;
	}

	public void setViews(List<Views> views) {
		this.views = views;
	}

}
