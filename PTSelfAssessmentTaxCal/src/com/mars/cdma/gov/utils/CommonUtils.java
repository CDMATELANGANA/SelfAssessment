package com.mars.cdma.gov.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.Weeks;

import com.mars.cdma.gov.bean.LookUp;

/**
 * <p>
 * Title: CommonUtils.java
 * </p>
 * <p>
 * Description: This class is a common utility functions
 * </p>
 * Copyright (c) 2010 for Mars Telecom Systems Pvt Ltd
 *
 * @version: 1.0
 * @author : Mars Telecom Systems
 * 
 */
public class CommonUtils {

	/** The log. */
	private static final Logger log= Logger.getLogger(CommonUtils.class);

	public static List getReportsCategory() {
		List<String> listReportCategory = new ArrayList<String>();

		listReportCategory.add("LMS");
		listReportCategory.add("Audit");

		return listReportCategory;
	}

	public static List getReportDataTypes() {
		List<String> listDataTypeCategory = new ArrayList<String>();
		listDataTypeCategory.add("Date");
		listDataTypeCategory.add("Double");
		listDataTypeCategory.add("Float");
		listDataTypeCategory.add("Integer");
		listDataTypeCategory.add("Long");
		listDataTypeCategory.add("String");

		return listDataTypeCategory;
	}
	
	public static List getInternalEntityNames() {
		List<String> listEntityNames = new ArrayList<String>();

		listEntityNames.add("Month");
		listEntityNames.add("NodeMaster");
		listEntityNames.add("Ward");
		listEntityNames.add("Sector");
		listEntityNames.add("FinancialYear");
		listEntityNames.add("CollectionCenter");
		listEntityNames.add("Stage");
		listEntityNames.add("PlotType");
		listEntityNames.add("Plot");
		listEntityNames.add("Hidden");
		listEntityNames.add("AUDIT_ENTITY_NAME");
		listEntityNames.add("AUDIT_PROPERTY_NAME");

		listEntityNames.add("RBD_CHART_TYPE");
		listEntityNames.add("RBD_BIRTHS_CHART_TYPE");
		listEntityNames.add("RBD_DEATHS_CHART_TYPE");
		listEntityNames.add("RBD_STILLBIRTHS_CHART_TYPE");
		listEntityNames.add("RBD_YEAR");
		listEntityNames.add("RBD_QUARTER");

		return listEntityNames;
	}

	/* This method is used in Reports */
	@SuppressWarnings("unchecked")
	public static List<String[]> getReportMonths() {
		List<String[]> alstrMonths = new ArrayList<String[]>();

		alstrMonths.add(new String[] { "1", "January" });
		alstrMonths.add(new String[] { "2", "February" });
		alstrMonths.add(new String[] { "3", "March" });
		alstrMonths.add(new String[] { "4", "April" });
		alstrMonths.add(new String[] { "5", "May" });
		alstrMonths.add(new String[] { "6", "June" });
		alstrMonths.add(new String[] { "7", "July" });
		alstrMonths.add(new String[] { "8", "August" });
		alstrMonths.add(new String[] { "9", "September" });
		alstrMonths.add(new String[] { "10", "October" });
		alstrMonths.add(new String[] { "11", "November" });
		alstrMonths.add(new String[] { "12", "December" });

		return alstrMonths;
	}

	public static List<String[]> getXAxisTimeParams()
    {
		ArrayList alstrMonths = new ArrayList();
		alstrMonths.add(new String[] { "MONTHLY", "MONTHLY" });
		alstrMonths.add(new String[] { "YEARLY", "YEARLY" });
		alstrMonths.add(new String[] { "DAILY", "DAILY" });
		return alstrMonths;
    }

	public static List<String[]> getTaxTypes()
    {
		ArrayList alstrTaxes = new ArrayList();
		alstrTaxes.add(new String[] { "PTAX", "Property Tax" });
		/*alstrTaxes.add(new String[] { "WTAX", "Water Tax" });*/

		return alstrTaxes;
    }

	public static List<String[]> getGradeCode()
    {
		ArrayList alstrGrades = new ArrayList();
		alstrGrades.add(new String[] { "1","Corporation"});
		alstrGrades.add(new String[] { "2","Muncipality"});
		alstrGrades.add(new String[] { "3","Nagara Panchayat"});
		
		return alstrGrades;
    }
	
	public static List<String[]> getRegionCode()
    {
		ArrayList alstrRegions = new ArrayList();
		alstrRegions.add(new String[] { "1","Hyderabad"});
		alstrRegions.add(new String[] { "2","Warangal"});
				
		return alstrRegions;
    }

	public static List<String[]> getDistricts()
    {
		ArrayList alstrDistricts = new ArrayList();
		alstrDistricts.add(new String[] { "Adilabad","Adilabad"});
		alstrDistricts.add(new String[] { "Karimnagar","Karimnagar"});
		alstrDistricts.add(new String[] { "Khammam","Khammam"});
		alstrDistricts.add(new String[] { "Mahaboobnagar","Mahaboobnagar"});
		alstrDistricts.add(new String[] { "Medak","Medak"});
		alstrDistricts.add(new String[] { "Nalgonda","Nalgonda"});
		alstrDistricts.add(new String[] { "Nizamabad","Nizamabad"});
		alstrDistricts.add(new String[] { "Rangareddy","Rangareddy"});
		alstrDistricts.add(new String[] { "Warangal","Warangal"});
		
		return alstrDistricts;
    }
	
		/**
	 * This is a static method used for comparing the 2 sets and returns the
	 * difference as another set.
	 *
	 * @param srcCollection
	 *            as Collection object
	 * @param destCollection
	 *            the dest collection
	 * @param MethodName
	 *            the method name
	 * @return collection Collection object.
	 */
	@SuppressWarnings("unchecked")
	public static Collection compareSets(Collection srcCollection,
			Collection destCollection, String MethodName) {
		/*
		 * if (log.isDebugEnabled()) {
		 * log.debug("compareSets method is called "); }
		 */
		// Declaring a new collectin object
		Collection collection = new HashSet();

		// cheking if destCollectin is not null and size >0
		if (destCollection != null && destCollection.size() > 0) {
			srcCollection = new HashSet(srcCollection);
			destCollection = new HashSet(destCollection);
			Iterator iterator = srcCollection.iterator();
			try {
				while (iterator.hasNext()) {
					Object objSrc = iterator.next();
					Iterator iterator4Dest = destCollection.iterator();
					String strSrcValue = "'"
							+ objSrc.getClass().getMethod(MethodName, null)
									.invoke(objSrc, null) + "'";
					while (iterator4Dest.hasNext()) {
						Object objDest = iterator4Dest.next();
						String strDestValue = "'"
								+ objDest.getClass()
										.getMethod(MethodName, null)
										.invoke(objDest, null) + "'";

						if (strSrcValue.equals(strDestValue)) {
							// log.debug("Object Matched and will be removed");
							collection.add(objSrc);
						}
					}
				}
			} catch (Exception exception) {
				log.error(exception.getMessage(), exception);
				exception.printStackTrace();
			}
			srcCollection.removeAll(collection);

			return srcCollection;
		}
		return srcCollection;
	}

	/**
	 * Gets the max page.
	 *
	 * @param longCount
	 *            the long count
	 * @return the max page
	 */
	public static long getMaxPage(long longCount) {
		long longMaxPage = -1;
		if (longCount > 0) {
			if ((longCount % Constants.DEFAULT_ROWS_PER_PAGE) == 0) {
				longMaxPage = (longCount / Constants.DEFAULT_ROWS_PER_PAGE);
			} else {
				longMaxPage = (longCount / Constants.DEFAULT_ROWS_PER_PAGE) + 1;
			}
		}
		return longMaxPage;
	}

	public static long getMaxPage(long longCount, long displayPageSize) {
		long longMaxPage = -1;
		if (longCount > 0) {
			if ((longCount % displayPageSize) == 0) {
				longMaxPage = (longCount / displayPageSize);
			} else {
				longMaxPage = (longCount / displayPageSize) + 1;
			}
		}
		return longMaxPage;
	}

	/**
	 * Check pagination attributes.
	 *
	 * @param request
	 *            the request
	 * @return the long
	 */
	public static long checkPaginationAttributes(HttpServletRequest request) {
		// Pagination related code.
		long longCurrentPage = 1;
		String strPaginationClicked = Constants.STATUS_NO_LABEL;

		if (request.getParameter("isPaginationClicked") != null
				&& request.getParameter("isPaginationClicked").length() > 0) {
			strPaginationClicked = request.getParameter("isPaginationClicked");
		}

		if (request.getParameter("currentPage") != null
				&& request.getParameter("currentPage").length() > 0
				&& strPaginationClicked
						.equalsIgnoreCase(Constants.STATUS_YES_LABEL)) {
			longCurrentPage = Long.parseLong(request
					.getParameter("currentPage").toString());
		}// end

		if (request.getParameter("isPaginationClicked") != null
				&& request.getParameter("isPaginationClicked").length() > 0) {
			strPaginationClicked = request.getParameter("isPaginationClicked");
		}

		if (request.getParameter("currentPage") != null
				&& request.getParameter("currentPage").length() > 0
				&& strPaginationClicked
						.equalsIgnoreCase(Constants.STATUS_YES_LABEL)) {
			longCurrentPage = Long.parseLong(request
					.getParameter("currentPage").toString());
		}// end
		return longCurrentPage;
	}

	/**
	 * This is a static method to get request parameter.
	 *
	 * @param request
	 *            the request
	 * @param strParameter
	 *            the str parameter
	 * @return Inward Number as String
	 */
	public static String getParameterFromRequest(HttpServletRequest request,
			String strParameter) {
		String strValue = request.getParameter(strParameter);
		if (strValue == null || strValue.length() == 0) {
			if (request.getAttribute(strParameter) != null) {
				strValue = request.getAttribute(strParameter).toString();
			}
		}
		return strValue;
	}

	/**
	 * This is a static method used to escape characters.
	 *
	 * @param strSearchString
	 *            as String
	 * @return strSearcgString as String
	 */
	public static String getEscapedSQLString(String strSearchString) {
		/*
		 * if (log.isTraceEnabled()) {
		 * log.trace("getEscapedString method is called "); }
		 */
		if (strSearchString != null && strSearchString.length() > 0) {
			strSearchString = StringEscapeUtils.escapeSql(strSearchString);
		}
		return strSearchString;
	}

	/**
	 * Returns Date object formatted for current system date
	 *
	 * @return
	 */
	public static Date getCurrentFormattedDate() {

		String dateStr = getCurrentStringDate();
		return CommonUtils.getFormattedDate(dateStr);
	}

	/**
	 * Returns String object of current date formatted
	 *
	 * @return
	 */
	public static String getCurrentStringDate() {

		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * This is a static method to get the date object for a give string
	 *
	 * @param strDate
	 *            as String
	 * @return date as formated date.
	 */

	public static Date getFormattedDate(String strDate) {

		Date date = null;
		try {
			if (strDate != null && strDate.length() > 0) {
				DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT, Locale.ENGLISH);
				date = dateFormat.parse(strDate);
			}
		} catch (Exception exception) {
			try {
				if (strDate != null && strDate.length() > 0) {
					DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
					date = dateFormat.parse(strDate);
				}
			} catch (ParseException e) {
				log.error(exception.getStackTrace(), e);
				e.printStackTrace();
				throw new RuntimeException(exception.getCause());
			}
		}
		return date;
	}
	public static Date getFormattedDateNEW(String strDate) {

		Date date = null;
		try {
			if (strDate != null && strDate.length() > 0) {
				DateFormat dateFormat = new SimpleDateFormat(Constants.NEW_DATE_FORMAT);
				date = dateFormat.parse(strDate);
			}
		} catch (Exception exception) {
			try {
				if (strDate != null && strDate.length() > 0) {
					DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
					date = dateFormat.parse(strDate);
				}
			} catch (ParseException e) {
				log.error(exception.getStackTrace(), e);
				e.printStackTrace();
				throw new RuntimeException(exception.getCause());
			}
		}
		return date;
	}
	
	public static Date getFormattedDateTime(String strDate) {

		Date date = null;
		try {
			if (strDate != null && strDate.length() > 0) {
				DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
				date = dateFormat.parse(strDate);
			}
		} catch (Exception exception) {
			try {
				if (strDate != null && strDate.length() > 0) {
					DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_TIME_FORMAT);
					date = dateFormat.parse(strDate);
				}
			} catch (ParseException e) {
				log.error(exception.getStackTrace(), e);
				e.printStackTrace();
				throw new RuntimeException(exception.getCause());
			}
		}
		return date;
	}
	

	public static Date getFormattedDateWithGivenFormat(String strDate,
			String format) {

		/*
		 * if (log.isDebugEnabled()) {
		 * log.debug("getFormattedDateWithGivenFormat method is called "); }
		 */

		Date date = null;
		try {
			if (strDate != null && strDate.length() > 0) {
				DateFormat dateFormat = new SimpleDateFormat(format);
				date = dateFormat.parse(strDate);
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return date;
	}

	public static String getFormattedStringDate(String strDate) {

		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date date = getFormattedDate(strDate);
		return dateFormat.format(date);
	}

	public static String getFormattedStringFromDate(Date date) {
		try {
			if (date != null) {
				DateFormat dateFormat = new SimpleDateFormat(
						Constants.DATE_FORMAT);
				return dateFormat.format(date);
			}
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			throw new RuntimeException(exception.getCause());
		}
		return "";
	}

	/**
	 * Returns String object of current date formatted as "dd/mm/yyyy"
	 *
	 * @return
	 */
	public static String getCurrentStringUIDate() {

		DateFormat dateFormat = new SimpleDateFormat(Constants.DATE_FORMAT);
		Date date = new Date();
		return dateFormat.format(date);
	}

	/**
	 * This is a static method to get the FinancialYearStartDate
	 *
	 * @return
	 */
	public static String getFinancialYearStartDate() {

		Date date = new Date();
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		int currentYear = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);

		int previousYear = currentYear - 1;

		if (month >= 3)
			return "01-Apr-" + currentYear;
		else
			return "01-Apr-" + previousYear;
	}

	public static boolean deleteFileFromSystem(String baseDir, String filePath,
			String fileName) throws Exception {
		String fullPath = "";
		// Delete file form The given Path
		if (baseDir != null && baseDir.length() > 0)
			fullPath = fullPath + baseDir + "/";

		if (filePath != null && filePath.length() > 0)
			fullPath = fullPath + filePath + "/";

		if (fileName != null && fileName.length() > 0)
			fullPath = fullPath + fileName;

		boolean success = true;
		File file = new File(fullPath);
		if (file.exists())
			success = file.delete();
		else
			// If file does not exist then we can say that this operation is
			// success
			success = true;
		return success;
	}

	public static long getDifferenceDays(String startDate, String endDate) {
		if (StringUtils.isEmpty(endDate))
			endDate = startDate;
		Date date1 = getFormattedDate(startDate);
		Date date2 = getFormattedDate(endDate);
		Date fromDate;
		Date toDate;
		if (date1.after(date2)) {
			fromDate = date1;
			toDate = date2;
		} else {
			fromDate = date2;
			toDate = date1;
		}

		long days = (fromDate.getTime() - toDate.getTime()) / 86400000;
		return days;
	}

	public static List<Date> getBetweenDates(String str_date, String end_date) {
		List<Date> dates = new ArrayList<Date>();
		try {
			if (StringUtils.isEmpty(end_date))
				end_date = str_date;
			DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
			Date startDate = (Date) formatter.parse(str_date);
			Date endDate = (Date) formatter.parse(end_date);
			long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
			long endTime = endDate.getTime(); // create your endtime here,
												// possibly using Calendar or
												// Date
			long curTime = startDate.getTime();
			while (curTime <= endTime) {
				dates.add(new Date(curTime));
				curTime += interval;
			}
		} catch (ParseException e) {
			log.error(e);
		}
		return dates;
	}

	public static List<Date> getBetweenDatesFromDateObjects(Date startDate,
			Date endDate) {
		List<Date> dates = new ArrayList<Date>();
		try {
			if (startDate != null && endDate != null) {
				long interval = 24 * 1000 * 60 * 60; // 1 hour in millis
				long endTime = endDate.getTime(); // create your endtime here,
													// possibly using Calendar
													// or Date
				long curTime = startDate.getTime();
				while (curTime < endTime) {
					dates.add(new Date(curTime));
					curTime += interval;
				}
			}
		} catch (Exception e) {
			log.error(e);
		}
		return dates;
	}

	public static String getFormattedStringFromDate(Date date, String DateFormat) {
		/*
		 * if (log.isTraceEnabled()) {
		 * log.trace("getFormattedStringFromDate method is called "); }
		 */
		try {
			if (date != null) {
				DateFormat dateFormat = new SimpleDateFormat(DateFormat);
				return dateFormat.format(date);
			}
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			throw new RuntimeException(exception.getCause());
		}
		return "";
	}

	// this function will give u future date from today, passing negative values
	// will decrease the date
	public static Date getFutureDateFromCurrentDate(int Years, int months,
			int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.add(Calendar.YEAR, Years);
		calendar.add(Calendar.MONTH, months);
		calendar.add(Calendar.DATE, days);
		String dateStr = getFormattedStringFromDate(calendar.getTime());
		return getFormattedDate(dateStr);
	}

	public static List<Long> prepareListFromStringValues(String value) {
		List<Long> list = new ArrayList<Long>();
		for (String s : value.split(","))
			list.add(new Long(s));
		return list;

	}

	// this function will give u future date from given date, passing negative
	// values will decrease the date
	public static Date getFutureDateFromGivenDate(Date givenDate, int Years,
			int months, int days) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(givenDate);
		calendar.add(Calendar.YEAR, Years);
		calendar.add(Calendar.MONTH, months);
		calendar.add(Calendar.DATE, days);
		String dateStr = getFormattedStringFromDate(calendar.getTime());
		return getFormattedDate(dateStr);
	}

	public static String getFutureDate(String str_date, int noOfDays) {
		String futureDate = "";
		try {
			DateFormat formatter = new SimpleDateFormat(Constants.DATE_FORMAT);
			Date startDate = (Date) formatter.parse(str_date);
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(startDate);
			calendar.add(Calendar.DAY_OF_YEAR, noOfDays);
			Date endDate = calendar.getTime();
			futureDate = getFormattedStringFromDate(endDate,
					Constants.DATE_FORMAT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return futureDate;
	}

	// this function will return u the financial yesr in two formats one is
	// YYYY-YY and other is YYYY-YYYY
	public static String getCurrentFinancialYear(String format) {
		Calendar calendar = Calendar.getInstance();
		log.debug(calendar.getTime());
		int currentYear = calendar.get(Calendar.YEAR);
		int previousYear = currentYear - 1;
		int nextYear = currentYear + 1;
		String preYear = "" + previousYear;
		String nexYear = String.valueOf(nextYear);
		String curYear = String.valueOf(currentYear);
		String FinYear = null;
		if (format.length() == 7) {
			if (calendar.get(Calendar.MONTH) > 3)
				FinYear = curYear + "-" + nexYear.charAt(2) + nexYear.charAt(3);
			else
				FinYear = preYear + "-" + curYear.charAt(2) + curYear.charAt(3);
			return FinYear.trim();
		}

		if (format.length() == 9) {
			if (calendar.get(Calendar.MONTH) > 3)
				FinYear = curYear + "-" + nexYear;
			else
				FinYear = preYear + "-" + curYear;
			return FinYear.trim();
		} else {
			log.debug("Format Not handled");
			return null;

		}

	}

	/**
	 * This is a static method to get the date object for a give string
	 *
	 * @param strDate
	 *            as String
	 * @return date as formated date.
	 */

	public static Date getGivenFormattedDate(String strDate, String givenFormat) {

		/*
		 * if (log.isDebugEnabled()) {
		 * log.debug("getGivenFormattedDate method is called "); }
		 */

		Date date = null;
		try {
			if (strDate != null && strDate.length() > 0) {
				DateFormat dateFormat = new SimpleDateFormat(givenFormat);
				date = dateFormat.parse(strDate);
			}
		} catch (Exception Exception) {
			return null;
		}
		return date;
	}

	public static String getGivenStringDateFormat(Date date, String givenFormat) {
		/*
		 * if (log.isDebugEnabled()) {
		 * log.debug("getGivenStringDateFormat method is called "); }
		 */

		try {
			if (date != null) {
				DateFormat dateFormat = new SimpleDateFormat(givenFormat);
				return dateFormat.format(date);
			}
		} catch (Exception exception) {
			log.error(exception.getStackTrace(), exception);
			throw new RuntimeException(exception.getCause());
		}
		return "";
	}

	public static int getNoOfWeeksBetweenDates(Date startDate, Date endDate) {

		DateTime dateTime1 = new DateTime(startDate);
		DateTime dateTime2 = new DateTime(endDate);

		return (Weeks.weeksBetween(dateTime1, dateTime2).getWeeks());

	}

	// Will return only the latest week, the change is to avoid multiple synchs
	// of the same week
	public static List getWeeksList(Date startDate, Date endDate) {

		List weeksList = new ArrayList();

		weeksList = getWeeksListAll(startDate, endDate);

		// returning only the latest (newest) week to avoid issues with multiple
		// week synchronization
		List finalWeeksList = weeksList.subList(weeksList.size() - 1,
				weeksList.size());
		return finalWeeksList;

		// return weeksList;
	}

	public static List getWeeksListAll(Date startDate, Date endDate) {

		List weeksList = new ArrayList();

		Calendar cStart = Calendar.getInstance();
		Calendar cEnd = Calendar.getInstance();
		Calendar cEndTemp = Calendar.getInstance();

		cStart.setTime(startDate);
		cEnd.setTime(endDate);
		int startMonth = cStart.get(Calendar.MONTH) + 1;
		int startYear = cStart.get(Calendar.YEAR);

		int weekDiff = CommonUtils.getNoOfWeeksBetweenDates(startDate, endDate);
		Date newStartDate = startDate;
		Date newEndDate = null;

		while (cStart.before(cEnd)) {
			int weekStartDay = cStart.get(Calendar.DAY_OF_WEEK);
			int dateDiff = 6;

			if (cStart.get(Calendar.DAY_OF_WEEK) != 1)
				dateDiff = 7 - weekStartDay;

			// Calendar.get(Calendar.DAY_OF_MONTH)
			// int currDate = newStartDate.getDate()+dateDiff;
			int currDate = cStart.get(Calendar.DAY_OF_MONTH) + dateDiff;
			newEndDate = CommonUtils.getFormattedDate(currDate + "-"
					+ startMonth + "-" + startYear);
			cEndTemp.setTime(newEndDate);
			// if((currDate!=newEndDate.getDate()) || (cEnd.before(cEndTemp))){
			if ((currDate != cEndTemp.get(Calendar.DAY_OF_MONTH))
					|| (cEnd.before(cEndTemp))) {
				if (cEnd.before(cStart) || (cEnd.before(cEndTemp)))
					currDate = cEnd.get(Calendar.DATE);
				else
					currDate = cStart.getActualMaximum(Calendar.DAY_OF_MONTH);

				newEndDate = CommonUtils.getFormattedDate(currDate + "-"
						+ startMonth + "-" + startYear);
				startMonth += 1;
				if (currDate == cStart.getActualMaximum(Calendar.DAY_OF_MONTH))
					currDate = 0;
			}

			List<String> subList = new ArrayList();

			// For REST Query
			subList.add(CommonUtils.getGivenStringDateFormat(newStartDate,
					"yyyy-MM-dd"));
			subList.add(CommonUtils.getGivenStringDateFormat(newEndDate,
					"yyyy-MM-dd"));

			// For Storing in DB
			subList.add(CommonUtils.getGivenStringDateFormat(newStartDate,
					"dd-MM-yyyy"));
			subList.add(CommonUtils.getGivenStringDateFormat(newEndDate,
					"dd-MM-yyyy"));

			weeksList.add(subList);
			newStartDate = CommonUtils.getFormattedDate((currDate + 1) + "-"
					+ startMonth + "-" + startYear);
			cStart.setTime(newStartDate);
			startMonth = cStart.get(Calendar.MONTH) + 1;
			startYear = cStart.get(Calendar.YEAR);
		}

		return weeksList;

		// return weeksList;
	}

	public static List<String> getTestPlanYears() {
		List<String> listTestPlanYears = new ArrayList<String>();

		int startYear = 2012;
		int endYear = getCurrentYear() + 1;

		while (endYear >= startYear) {
			listTestPlanYears.add("" + endYear);
			endYear = endYear - 1;
		}

		return listTestPlanYears;
	}

	public static int getCurrentYear() {
		Calendar calendar = Calendar.getInstance();
		int currentYear = calendar.get(Calendar.YEAR);
		return currentYear;
	}

	public static List<String[]> getDays() {
		List<String[]> alstrDays = new ArrayList<String[]>();

		alstrDays.add(new String[] { "1", "Sunday" });
		alstrDays.add(new String[] { "2", "Monday" });
		alstrDays.add(new String[] { "3", "Tuesday" });
		alstrDays.add(new String[] { "4", "Wednesday" });
		alstrDays.add(new String[] { "5", "Thursday" });
		alstrDays.add(new String[] { "6", "Friday" });
		alstrDays.add(new String[] { "7", "Saturday" });

		return alstrDays;
	}

	public static void deleteFolders(String BaseDir) {
		try {
			if (BaseDir != null && BaseDir.length() > 0) {
				log.debug("SessionListener :: deleteFolders() :: Reports DIR Path for this Session = "
						+ BaseDir);
				File reportsDir = new File(BaseDir);
				boolean success = true;
				if (reportsDir.isDirectory()) {
					String[] children = reportsDir.list();
					log.debug("SessionListener :: deleteFolders() :: It is a directory and no of files in it are = "
							+ children.length);
					for (int k = 0; k < children.length; k++) {
						new File(reportsDir, children[k]).delete();
					}
					success = reportsDir.delete();
					log.debug("SessionListener :: deleteFolders() :: Report deletion status = "
							+ success);
					if (success) {
						log.info("Session named Directory deleted.  : "
								+ BaseDir);
					}
				} else
					log.debug(BaseDir + " is not a directory");
			} else {
				log.info("Directory is not found to delete : " + BaseDir);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Session Dir deletion exception  : ", e);
		}
	}

	public static void deleteFilesInFolder(String BaseDir) {
		try {
			if (BaseDir != null && BaseDir.length() > 0) {
				log.debug("SessionListener :: deleteFolders() :: Reports DIR Path for this Session = "
						+ BaseDir);
				File reportsDir = new File(BaseDir);
				boolean success = true;
				if (reportsDir.isDirectory()) {
					String[] children = reportsDir.list();
					log.debug("SessionListener :: deleteFolders() :: It is a directory and no of files in it are = "
							+ children.length);
					for (int k = 0; k < children.length; k++) {
						new File(reportsDir, children[k]).delete();
					}
				} else
					log.debug(BaseDir + " is not a directory");
			} else {
				log.info("Directory is not found to delete files : " + BaseDir);
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			log.error("Session Dir files deletion exception  : ", e);
		}
	}

	public static List<String> getStringListFromLookUp(List<LookUp> listLookup) {
		List<String> listValues = new ArrayList<String>();
		for (LookUp lookUp : listLookup) {
			listValues.add(lookUp.getLookUpValue());
		}

		return listValues;
	}
	public static List<String[]> getStatus() {

		List<String[]> alstrStatus = new ArrayList<String[]>();

		alstrStatus.add(new String[] { "0", "InActive" });
		alstrStatus.add(new String[] { "1", "Active" });
		alstrStatus.add(new String[] { "2", "Cancelled" });
		alstrStatus.add(new String[] { "3", "Cancel Pending" });
		alstrStatus.add(new String[] { "4", "Deleted" });
		alstrStatus.add(new String[] { "5", "Not Used" });

		return alstrStatus;
	}

	public static HashMap<Integer,String> getServerLicenseStatus() {

		HashMap<Integer,String> alstrStatus = new HashMap<Integer,String>();

		alstrStatus.put(new Integer(1), "Active" );
		alstrStatus.put(new Integer(2), "Cancelled" );

		return alstrStatus;
	}

	public static List<String[]> getProductStatus() {

		List<String[]> alstrStatus = new ArrayList<String[]>();

		alstrStatus.add(new String[] { "0", "InActive" });
		alstrStatus.add(new String[] { "1", "Active" });

		return alstrStatus;
	}

	public static List<String[]> getServerStatus() {

		List<String[]> alstrStatus = new ArrayList<String[]>();

		alstrStatus.add(new String[] { "1", "Active" });
		alstrStatus.add(new String[] { "2", "Cancelled" });

		return alstrStatus;
	}

	public static List<String[]> getSiteLicenseStatus() {

		List<String[]> siteLicenseStatus = new ArrayList<String[]>();

		siteLicenseStatus.add(new String[] { "0", "Pending" });
		siteLicenseStatus.add(new String[] { "1", "Approved" });

		return siteLicenseStatus;
	}

	public static List<String[]> getCollectionType() {

		List<String[]> collectionType = new ArrayList<String[]>();

		collectionType.add(new String[] { "1", "Credit Card" });
		collectionType.add(new String[] { "2", "Paypal" });

		return collectionType;
	}

	public static List<String[]> getUserLicenseStatus() {

		List<String[]> userLicenseStatus = new ArrayList<String[]>();

		userLicenseStatus.add(new String[] { "1", "Active" });
		userLicenseStatus.add(new String[] { "2", "Cancelled" });
		userLicenseStatus.add(new String[] { "3", "Not Used" });

		return userLicenseStatus;
	}

	public static boolean verifyCaptcha(String gRecaptchaResponse) {

		boolean verified = false;

		try {
			String url = ApplicationSettings.receptchaURL+"?secret="+ApplicationSettings.receptchaSecretkey+"&response="+gRecaptchaResponse;
			URL obj = new URL(url);
			HttpURLConnection con = (HttpURLConnection) obj.openConnection();

			// optional default is GET
			con.setRequestMethod("GET");
			int responseCode = con.getResponseCode();
			BufferedReader in = new BufferedReader(new InputStreamReader(
					con.getInputStream()));
			String inputLine;
			StringBuffer captchaResponse = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				captchaResponse.append(inputLine);
			}
			in.close();

			// print result
			if (captchaResponse.toString().indexOf("true") > 0)
				verified = true;

		} catch (IOException ioe) {
			ioe.printStackTrace();
		}

		return verified;
	}

	public static List<String[]> getDisplayPageSize() {

		List<String[]> displayPageSize = new ArrayList<String[]>();

		displayPageSize.add(new String[] { "2", "2" });
		displayPageSize.add(new String[] { "12", "12" });
		displayPageSize.add(new String[] { "15", "15" });
		displayPageSize.add(new String[] { "20", "20" });
		displayPageSize.add(new String[] { "25", "25" });
		displayPageSize.add(new String[] { "30", "30" });

		return displayPageSize;
	}

	/**
	 * @param response
	 * @param licenseInfo
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	public static void generateLicernseInfoFile(HttpServletResponse response,
			String licenseInfo) throws IOException,
			UnsupportedEncodingException {
		String mimeType = "text/xml";
		if (mimeType == null) {
		    // set to binary type if MIME mapping not found
		    mimeType = "application/octet-stream";
		}
		System.out.println("MIME type: " + mimeType);

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) licenseInfo.length());

		String fileName = "licenseInfo.xml";

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"" + fileName +"\"");
		response.setHeader(headerKey, headerValue);
		// get output stream of the response

		OutputStream outStream = response.getOutputStream();


		//byte[] buffer = new byte[BUFFER_SIZE];
		int bytesRead = -1;
		InputStream inputStream = new ByteArrayInputStream(licenseInfo.getBytes("UTF-8"));
		byte[] input = licenseInfo.getBytes();

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(input)) != -1) {
		    outStream.write(input, 0, bytesRead);
		}

		response.getOutputStream().flush();
		response.getOutputStream().close();
		inputStream.close();
	}


	public static void generateEncryptedLicernseInfoFile(HttpServletResponse response, byte[] licenseInfo) throws IOException,
			UnsupportedEncodingException {

		String mimeType = "text/xml";
		if (mimeType == null) {
		    // set to binary type if MIME mapping not found
		    mimeType = "application/octet-stream";
		}

		// set content attributes for the response
		response.setContentType(mimeType);
		response.setContentLength((int) licenseInfo.length);

		String fileName = "licenseInfo.xml";

		// set headers for the response
		String headerKey = "Content-Disposition";
		String headerValue = String.format("attachment; filename=\"" + fileName +"\"");
		response.setHeader(headerKey, headerValue);
		// get output stream of the response

		OutputStream outStream = response.getOutputStream();

		int bytesRead = -1;
		InputStream inputStream = new ByteArrayInputStream(licenseInfo);

		// write bytes read from the input stream into the output stream
		while ((bytesRead = inputStream.read(licenseInfo)) != -1) {
		    outStream.write(licenseInfo, 0, bytesRead);
		}

		response.getOutputStream().flush();
		response.getOutputStream().close();
		inputStream.close();
	}

	public static List<String[]> getActivationTypes() {

		List<String[]> activationTypes = new ArrayList<String[]>();

		activationTypes.add(new String[] { "ONLINE", "ONLINE" });
		activationTypes.add(new String[] { "OFFLINE", "OFFLINE" });

		return activationTypes;
	}
	public static List<String[]> getRecurringList() {

		List<String[]> recurringList = new ArrayList<String[]>();

		recurringList.add(new String[] { "1", "Recurring" });
		recurringList.add(new String[] { "0", "Non-Recurring" });


		return recurringList;
	}

	public static Locale getLocale(HttpServletRequest request){
		if(request.getParameter("lang") != null && request.getParameter("lang")!=""){
			return new Locale(request.getParameter("lang"));
		}else if(request.getSession().getAttribute("lang") != null && request.getSession().getAttribute("lang")!=""){
			return new Locale((String)request.getSession().getAttribute("lang"));
		}
		return new Locale("en");
	}
	

public static String getTransactionType(String transactionCode){
	String payModeVal=null;
	switch (transactionCode) {
	
	 
	case "01":
		payModeVal="Netbanking";	
	break;
	case "02":
		payModeVal="Credit Card";	
		break;
	case "03":
		payModeVal="Debit Card";
		break;	
	case "04":
		payModeVal="Cash Card";	
		break;
	case "05":
		payModeVal="Mobile Wallet";	
		break;
	case "06":
		payModeVal="IMPS";	
		break;
	case "07":
		payModeVal="Reward Points";	
		break;
	case "08":
		payModeVal="Rupay";	
		break;
	case "09":
		payModeVal="--";	
		break;
	case "10":
		payModeVal="--";	
		break;
	case "NB":
		payModeVal="Netbanking";	
	break;
	case "CC":
		payModeVal="Credit Card";	
		break;
	case "DC":
		payModeVal="Debit Card";	
		break;	
	case "CASH":
		payModeVal="Cash Card";	
		break;
	case "EMI":
		payModeVal="EMI";	
		break;
	case "WALLET":
		payModeVal="WALLET";	
		break;
	case "IMPS":
		payModeVal="Mobile Banking";	
		break;

	
	}
	return payModeVal;
	
}
	public static String getUrl() {
	
		//try {
		/*InetAddress inetAddress = InetAddress.getLocalHost();
		String ip=inetAddress.getHostAddress();
		System.out.println("IP Address "+ip);*/
		//String url="http://125.18.179.58:8081"; //Local
		
			String url="http://125.18.179.59:8081"; //Live Mutation-selefassess-ugd
		//String url="http://epaycdma.telangana.gov.in:8081";
		//String url="http://epaycdma.telangana.gov.in";
	 
	/*
		} catch (Exception e) {
		e.printStackTrace();
		}*/
		
		return url;
	}
}
