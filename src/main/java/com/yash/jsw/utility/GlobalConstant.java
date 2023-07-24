package com.yash.jsw.utility;

public interface GlobalConstant {
	Integer DEFAULT_FIRE_TIME = 1;
	Integer WRONG_USERNAME_PASSWORD = 100;
	Integer SUCCESSFUL_LOGOUT = 205;
	Integer SUCCESSFUL_LOGIN = 200;
	Integer ACCESS_DENIED = 401;
	String JOB_GROUP ="accelerator-group";
	Integer SMS = 0;
	Integer HSM = 1;
	
	Integer OBJECT_FOUND = 200;
	Integer OBJECT_NOT_FOUND = 0;
	
	String YEARLY ="yearly";
	String MONTHLY ="monthly";
	String WEEKLY ="weekly";
	String DAILY ="daily";
	String HOURLY ="hourly";
	String MINUTELY ="minutes";
	String success  = "success";
	

	public enum TriggerType {
		 SMS_TRIGGER,HMS_TRIGGER
	}
	
	
	public enum JobStatus {
		STARTED, SCHEDULED, STOPPED,PAUSED
	}
	 String JOB_PARAMETERS_REGEX = "([\\w\\.-_\\)\\(]+=[^,\\n]*[,\\n])*([\\w\\.-_\\)\\(]+=[^,]*$)";

    /**
     * job name
     */
    String JOB_NAME = "jobName";

    /**
     * job run date
     */
    String JOB_RUN_DATE = "jobRunDate";

    /**
     * Quartz group
     */
    String QUARTZ_GROUP = "quartzGroup";

    /**
     * Quartz trigger name suffix
     */
    String TRIGGER_SUFFIX = "QuartzTrigger";

    /**
     * Bean names
     */
    String JOB_SERVICE_BEAN = "jobService";
    String JOB_DATASTORE_BEAN = "batchDataStore";
    
     /**
     * Messages
     */
    String JOB_IS_NOT_SCHEDULED = "Not Scheduled";
    String JOB_IS_SCHEDULED = "Scheduled";
    
    /**
     * Action
     */
    String ACTION_SCHEDULE = "Schedule";
    String ACTION_UNSCHEDULE = "Un-Schedule";
    
	Integer ACTION_SUCCESSFUL = 200;
	Integer OBJECT_DUPLICATE = 409;
	
	String SCHEDULED = "Scheduled";
	String INPROCESS= "Inprocess";
	String COMPLETED = "Completed";

}
