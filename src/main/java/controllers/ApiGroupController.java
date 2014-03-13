/**
 * Copyright (C) 2012 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import ninja.Result;
import ninja.Results;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import models.Tgroup;
import dao.GroupDao;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.spec.InvalidKeySpecException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import ninja.Context;
import ninja.i18n.Messages;
import ninja.params.Param;
import ninja.params.PathParam;


@Singleton
public class ApiGroupController extends BaseController {
      
    @Inject
    ApiGroupController (Messages msg) {
        this.msg = msg;
    }

    @Inject
    GroupDao groupDao;
    
    
    public Result postNewGroup(@Param("groupname") String groupname,
                            @Param("groupitemsnumber") String groupitemsnumber,
                            @Param("groupdatecreation") String groupdatecreation,
                            Context context) throws ParseException, NoSuchAlgorithmException, InvalidKeySpecException, NoSuchProviderException {
        
        System.out.print("AAAAAAAAA -> " + groupname + "  " + groupitemsnumber + "  " + groupdatecreation);
        Short grpItemsNumber = Short.parseShort(groupitemsnumber);
        SimpleDateFormat textFormat = new SimpleDateFormat("dd.MM.yyyy");
        Date dateCreation;
        dateCreation = textFormat.parse(groupdatecreation);
        
        Tgroup group = groupDao.postNewGroup(groupname, grpItemsNumber, dateCreation);
        
        return Results.json().render(group);
    }
    
    public Result postDeleteGroup(@Param("") String zzzz,
                            Context context) {
        
        return Results.text().renderRaw("");
    }
    
}
