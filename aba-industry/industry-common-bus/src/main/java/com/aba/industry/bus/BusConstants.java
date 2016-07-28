/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for
 * the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.bus;

public enum BusConstants {
    /**
     * <pre>
     * ICANHAZ?
     * </pre>
     */
    LOG_CONFIG_REQUEST( "logger-config-request" ),

    /**
     * <pre>
     * {
     *   "level": .*,
     *   "prefix": .*,
     *   "file": .*
     * }
     * </pre>
     */
    LOG_CONFIG_RESPONSE( "logger-config-response" ),

    /**
     * XXX Currently not used, change this
     */
    LOG_CONFIG_CHANGE( "logger-config-change" ),

    /**
     * <pre>
     * ICANHAZ?
     * </pre>
     */
    SALARY_CONFIG_REQUEST( "salary-config-request" ),

    /**
     * <pre>
     * {
     *   "hoursPerT1ManuPoint": \d+,
     *   "hoursPerT2ManuPoint": \d+,
     *   "hoursPerT3ManuPoint": \d+,
     *   "hoursPerInventionPoint": \d+,
     *   "hoursPerCopyPoint": \d+,
     *   "hoursPerMEPoint": \d+,
     *   "hoursPerTEPoint": \d+,
     *   "hoursPerREPoint": \d+,
     *   "pointValue": \d+
     * }
     * </pre>
     */
    SALARY_CONFIG_RESPONSE( "salary-config-response" ),

    /**
     * <pre>
     * ICANHAZ?
     * </pre>
     */
    FETCH_META_CONFIG_REQUEST( "fetch-meta-config-request" ),

    /**
     * <pre>
     * "fetching": {
     *   "userAgent": .*
     * }
     * </pre>
     */
    FETCH_META_CONFIG_RESPONSE( "fetch-meta-config-response" ),

    //POI = PRODUCTS OF INTEREST
    /**
     * <pre>
     * ICANHAZ?
     * </pre>
     */
    POI_CONFIG_REQUEST( "poi-config-request" ),

    /**
     * <pre>
     * [{
     * "productName": .*,
     * "typeId": \d+,
     * "blueprintSkills": {
     *   "1": [{
     *     "typeid": \d+,
     *     "name": .*,
     *     "level": \d+
     *   }],
     *   "3": [{
     *     "typeid": \d+,
     *     "name": .*,
     *     "level": \d+
     *   }],
     *   "4": [{
     *     "typeid": \d+,
     *     "name": .*,
     *     "level": \d+
     *   }],
     *   "5": [{
     *     "typeid": \d+,
     *     "name": .*,
     *     "level": \d+
     *   }],
     *   "8": [{
     *     "typeid": \d+,
     *     "name": .*,
     *     "level": \d+
     *   }]
     * },
     * "blueprintDetails": {
     *   "maxProductionLimit": \d+,
     *   "productTypeID": \d+,
     *   "productTypeName": .*,
     *   "productQuantity": \d+,
     *   "times": {
     *     "1": \d+,
     *     "3": \d+,
     *     "4": \d+,
     *     "5": \d+
     *   },
     *   "facilities": [.*],
     *   "techLevel": \d+,
     *   "adjustedPrice": \d+.\d+,
     *   "precursorAdjustedPrice": \d+.\d+,
     *   "precursorTypeId": \d+,
     *   "probability": 0.\d+
     * },
     * "activityMaterials": {
     *   "1": [{
     *      "typeid": \d+,
     *      "name": .*,
     *      "quantity": \d+,
     *      "maketype": \d+
     *   }],
     *   "3": [{
     *      "typeid": \d+,
     *      "name": .*,
     *      "quantity": \d+,
     *      "maketype": \d+
     *   }],
     *   "4": [{
     *      "typeid": \d+,
     *      "name": .*,
     *      "quantity": \d+,
     *      "maketype": \d+
     *   }],
     *   "5": [{
     *      "typeid": \d+,
     *      "name": .*,
     *      "quantity": \d+,
     *      "maketype": \d+
     *   }],
     *   "8": [{
     *      "typeid": \d+,
     *      "name": .*,
     *      "quantity": \d+
     *   }]
     * },
     * "decryptors": [{
     *     "name": "Accelerant Decryptor",
     *     "typeid": 34201,
     *     "multiplier": 1.2,
     *     "me": 2,
     *     "te": 10,
     *     "runs": 1
     *   }, {
     *     "name": "Attainment Decryptor",
     *     "typeid": 34202,
     *     "multiplier": 1.8,
     *     "me": -1,
     *     "te": 4,
     *     "runs": 4
     *   }, {
     *     "name": "Augmentation Decryptor",
     *     "typeid": 34203,
     *     "multiplier": 0.6,
     *     "me": -2,
     *     "te": 2,
     *     "runs": 9
     *   }, {
     *     "name": "Parity Decryptor",
     *     "typeid": 34204,
     *     "multiplier": 1.5,
     *     "me": 1,
     *     "te": -2,
     *     "runs": 3
     *   }, {
     *     "name": "Process Decryptor",
     *     "typeid": 34205,
     *     "multiplier": 1.1,
     *     "me": 3,
     *     "te": 6,
     *     "runs": 0
     *   }, {
     *     "name": "Symmetry Decryptor",
     *     "typeid": 34206,
     *     "multiplier": 1,
     *     "me": 1,
     *     "te": 8,
     *     "runs": 2
     *   }, {
     *     "name": "Optimized Attainment Decryptor",
     *     "typeid": 34207,
     *     "multiplier": 1.9,
     *     "me": 1,
     *     "te": -2,
     *     "runs": 2
     *   }, {
     *     "name": "Optimized Augmentation Decryptor",
     *     "typeid": 34208,
     *     "multiplier": 0.9,
     *     "me": 2,
     *     "te": 0,
     *     "runs": 7
     *   }]
     * }]
     * </pre>
     */
    POI_CONFIG_RESPONSE( "poi-config-response" ),

    /**
     * XXX This isn't used just yet, its referenced currently but nothing does anything with it.
     */
    POI_CONFIG_CHANGE( "poi-config-change" ),

    /**
     * <pre>
     * {
     *   typeId: <\d+>,
     *   rows: [],
     *   regionId: <\d+>,
     *   generatedAt: <timestamp>,
     *   columns: []
     * }
     * </pre>
     */
    EMDR_INTERESTING_MESSAGE( "emdr-interesting-message" ),


    BUILD_CALC_REQUEST( "build-calc-request" ),

    /**
     * <pre>
     * {
     *   typeId: <\d+>,
     *   productName: <.*>,
     *   manufactureCost: <\d+.\d{2}>,
     *   inventionCost: <\d+.\d{2}>,
     *   salary: <\d+.\d{2}
     * }
     * </pre>
     */
    BUILD_CALC_RESPONSE( "build-calc-response" ),

    /**
     * <pre>
     * AREULISTEN?
     * </pre>
     */
    MODULES_ONLINE_REQUEST( "modules-online-request" ),

    /**
     * <pre>
     * { moduleName: .* }
     * </pre>
     */
    MODULE_ONLINE( "module-online" ),

    /**
     * <pre>
     * ICANHAZ?
     * </pre>
     */
    SALES_PRICES_REQUEST( "sales-price-request" ),

    /**
     * <pre>
     * [{
     *   typeId: \d+,
     *   productName: .*,
     *   jitaSell: \d+.\d+,
     *   amarrSell: \d+.\d+,
     *   dodixieSell: \d+.\d+,
     *   rensSell: \d+.\d+
     * }]
     * </pre>
     */
    SALES_PRICES_RESPONSE( "sales-price-response" ),

    /**
     * <pre>
     * {
     *   module: .*,
     *   category: TRACE|DEBUG|INFO|WARNING|ALERT|ERROR|EMERGENCY,
     *   message: .*
     * }
     * </pre>
     */
    LOG_MESSAGE( "log-message" );

    private String runtimeName;

    BusConstants ( String runtimeName ) {
        this.setRuntimeName( runtimeName );
    }

    public String getRuntimeName ( ) {
        return runtimeName;
    }

    public void setRuntimeName ( String runtimeName ) {
        this.runtimeName = runtimeName;
    }
}
