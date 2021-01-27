package com.finadv.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.finadv.entities.ReferralProgram;

/**
 * @author atanu
 *
 */
@Repository
public interface ReferralProgramRepository extends CrudRepository<ReferralProgram, Integer>{

	@Query(value="SELECT * FROM referral_program r WHERE r.user_id = :userId", nativeQuery = true)
	ReferralProgram getCodeByUserId(@Param("userId") long userId);
	
	@Query(value="SELECT * FROM referral_program r WHERE r.referral_code = :referralCode", nativeQuery = true)
	ReferralProgram getCodeByReferralCode(@Param("referralCode") String referralCode);
}
