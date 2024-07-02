package com.jayway.hopelink.campaign.contract.repository;

import com.jayway.hopelink.campaign.contract.document.CampaignDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CampaignRepository extends MongoRepository<CampaignDocument, String> {

}
