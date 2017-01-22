/*
 * Copyright 2017 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.api.endpoint;

import com.aba.data.domain.api.Asset;
import com.tlabs.eve.api.AssetListResponse;
import com.tlabs.eve.api.corporation.CorporationAssetsRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by maurerit on 1/8/17.
 */
public class TlabsCorporationAssetsEndpoints extends AbstractEveXmlApi implements CorporationAssetsEndpoints {

    @Override
    public List<Asset> retrieveAssets ( String keyId, String verificationCode, Long corporationId ) {
        CorporationAssetsRequest request = new CorporationAssetsRequest( corporationId.toString() );
        request.setKeyID( keyId );
        request.setKey( verificationCode );
        AssetListResponse response = getEveNetwork().execute( request );

        final List<Asset> assets = new ArrayList<>();

        if ( response.getAssets() != null ) {
            response.getAssets().forEach( apiAsset -> assets.add(convertFromTlabs( apiAsset )) );
        }

        return assets;
    }

    private Asset convertFromTlabs ( com.tlabs.eve.api.Asset apiAsset ) {
        Asset asset = new Asset();

        asset.setId( null );
        asset.setInventoryFlag( apiAsset.getInventoryFlag() );
        asset.setLocationId( apiAsset.getLocationID() );
        asset.setLocationName( apiAsset.getLocationName() );
        asset.setPackaged( apiAsset.isPackaged() );
        asset.setQuantity( apiAsset.getQuantity() );
        asset.setRawQuantity( apiAsset.getRawQuantity() );
        asset.setTypeId( (int)apiAsset.getItemID() );

        if ( apiAsset.getAssets() != null ) {
            asset.setItems( new ArrayList<>() );
            apiAsset.getAssets().stream().forEach( nestedAsset -> asset.getItems().add( convertFromTlabs( nestedAsset ) ) );
        }

        return asset;
    }
}
