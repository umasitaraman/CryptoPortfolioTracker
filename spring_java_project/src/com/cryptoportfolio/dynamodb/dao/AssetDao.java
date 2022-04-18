package com.cryptoportfolio.dynamodb.dao;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.model.Select;
import com.cryptoportfolio.dynamodb.models.Asset;
import com.cryptoportfolio.exceptions.AssetNotFoundException;

import javax.inject.Inject;
import java.util.List;

/**
 * The AssetDao Class fetches the Asset details from the DynamoDB table
 */

public class AssetDao {
    private final DynamoDBMapper dynamoDBMapper;

    /**
     * Instantiates an AssetDao object
     */

    @Inject
    public AssetDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    /**
     *
     * @param assetId Requires and Asset Id input parameter
     * @return Returns an Asset Object
     */

    public Asset getAsset (String assetId) {
        Asset asset = this.dynamoDBMapper.load(Asset.class, assetId);

        if (asset == null) {
            throw new AssetNotFoundException();
        }

        return asset;
    }

    public List<Asset> getAllAssets() {
        DynamoDBScanExpression dynamoDBScanExpression = new DynamoDBScanExpression();
        dynamoDBScanExpression.setSelect(Select.ALL_ATTRIBUTES);

        return dynamoDBMapper.scan(Asset.class, dynamoDBScanExpression);
    }

    public void batchSaveAssets(List<Asset> assets) {
        dynamoDBMapper.batchSave(assets);
    }
}
