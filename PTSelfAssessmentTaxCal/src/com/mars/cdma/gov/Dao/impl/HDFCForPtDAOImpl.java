/**
 * 
 */
package com.mars.cdma.gov.Dao.impl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mars.cdma.gov.Dao.HDFCForPtDAO;
import com.mars.cdma.gov.bean.AssessmentMaster;
import com.mars.cdma.gov.bean.Districts;
import com.mars.cdma.gov.bean.PaymentTransaction;
import com.mars.cdma.gov.bean.Ulbs;
import com.mars.cdma.gov.service.Assessmentservice;
import com.mars.cdma.gov.service.DistrictsService;
import com.mars.cdma.gov.service.GradeService;
import com.mars.cdma.gov.service.PaymentTransactionService;
import com.mars.cdma.gov.service.UlbsService;

/**
 * @author SARITA
 *
 */
@Repository
public class HDFCForPtDAOImpl implements HDFCForPtDAO{
	private static final Logger log = Logger.getLogger(AssessmentDAOImpl.class);
	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	private Assessmentservice assessmentservice;
	@Autowired
	private UlbsService ulbService;
	@Autowired
	private GradeService gradeService;
	@Autowired
	private static PaymentTransactionService paymentTransactionService;
	@Autowired
	private DistrictsService districtsService;


	@Override
	public PaymentTransaction hdfcInserttransaction(long uniqReqNumber,
			HttpServletRequest request, HttpServletResponse response) {
		PaymentTransaction paymentTransactionEntity = new PaymentTransaction();
		AssessmentMaster ptAssessmentMaster = getrsinglerecord(uniqReqNumber);
		System.out.println("ptAssessmentMaster" + ptAssessmentMaster);
		paymentTransactionEntity.setGateway_name("HDFC");
		if (paymentTransactionEntity.getGateway_name() == "HDFC") {
			// paymentTransactionEntity.setTransactionmode("I");

			paymentTransactionEntity.setC_AMTPAIDAT("O");
		}
		paymentTransactionEntity.setTotal_amount(ptAssessmentMaster
				.getPaymentamount());
		paymentTransactionEntity.setPaid_update_flag('N');

		paymentTransactionEntity.setReceipt_owner_door_no(ptAssessmentMaster
				.getOwnerDoorNo());
		paymentTransactionEntity.setReceipt_owner_name(ptAssessmentMaster
				.getOwnerSurName().concat(" ")
				.concat(ptAssessmentMaster.getOwnerName()));
		paymentTransactionEntity
				.setTransaction_mobile_number(ptAssessmentMaster
						.getOwnerMobile());
		paymentTransactionEntity.setTransaction_flag('N');
		paymentTransactionEntity.setUlbcode(ptAssessmentMaster.getUlbcode());
		paymentTransactionEntity.setUniqueRequestNumber(ptAssessmentMaster
				.getUniqueRequestNumber());
		paymentTransactionEntity.setC_delflag('N');
		paymentTransactionEntity.setTransdate(new java.util.Date());

		Ulbs ulbs = ulbService.getByUlbname(paymentTransactionEntity
				.getUlbcode());
		Districts districts = districtsService.get(ulbs.getDistrict_id());

		paymentTransactionEntity.setUlbname(ulbs.getUlbName());

		Session session = sessionFactory.openSession();

		session.save(paymentTransactionEntity);
		return paymentTransactionEntity;
	}


	private AssessmentMaster getrsinglerecord(long uniqReqNumber) {
		Session session = sessionFactory.openSession();
	    session.beginTransaction();
		Criteria criteria = session.createCriteria(AssessmentMaster.class);
		criteria.add(Restrictions.eq("uniqueRequestNumber", uniqReqNumber));
		System.out.println("uniquerequestid" + uniqReqNumber);

		AssessmentMaster getsinglerecord = (AssessmentMaster) criteria
				.uniqueResult();

		return getsinglerecord;
	}

}
