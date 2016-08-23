/*
 * Copyright 2016 maurerit
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the specific language governing permissions and limitations under the License.
 */

package com.aba.industry.rethink;

import com.rethinkdb.RethinkDB;
import com.rethinkdb.net.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * Created by maurerit on 8/22/16.
 */
@Component
public class RethinkInitializer {
    private static final RethinkDB r = RethinkDB.r;
    @Autowired
    private RethinkDBConnectionFactory connectionFactory;

    @PostConstruct
    public void createDb ( ) {
        Connection connection = connectionFactory.createConnection();
        List<String> dbList = r.dbList()
                               .run( connection );
        if ( !dbList.contains( "industrybot" ) ) {
            r.dbCreate( "industrybot" )
             .run( connection );
        }

        List<String> tables = r.db( "industrybot" )
                               .tableList()
                               .run( connection );
        if ( !tables.contains( "preferences" ) ) {
            r.db( "industrybot" )
             .tableCreate( "preferences" )
             .run( connection );
        }
    }
}
